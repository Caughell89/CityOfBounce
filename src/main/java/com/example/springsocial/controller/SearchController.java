package com.example.springsocial.controller;

import com.example.springsocial.model.Product;
import com.example.springsocial.model.ProductReview;
import com.example.springsocial.model.SimpleLocation;
import com.example.springsocial.payload.SearchRequest;
import com.example.springsocial.repository.CompanyRepository;
import com.example.springsocial.repository.LocationRepository;
import com.example.springsocial.repository.ProductRepository;
import com.example.springsocial.repository.ProductReviewRepository;
import com.example.springsocial.repository.SimpleLocationRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private SimpleLocationRepository simpleLocationRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ProductReviewRepository productReviewRepository;

    @GetMapping("/products/search/{location}")
    public List<Product> getProductsBySearchString(@PathVariable String location) {
        String state = location.substring(location.length() - 2);
        String city = location.substring(0, location.length() - 3);
        List<Product> foundProducts = new ArrayList();
        city = city.replaceAll("_", " ");

        foundProducts = productRepository.getProductsBySearchString(city, state);

        for (int i = 0; i < foundProducts.size(); i++) {
            System.out.println("ProductId: " + foundProducts.get(i).getProductId());
            List<ProductReview> pr = productReviewRepository.findByProductId(foundProducts.get(i).getProductId());
            foundProducts.get(i).setProductReviews(pr);
        }

        return foundProducts;
    }

    @CrossOrigin
    @PostMapping("/products/search")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public List<Product> getProductsBySearchFilters(@RequestBody SearchRequest searchRequest) {
        System.out.println(searchRequest.getLocation());
        System.out.println(searchRequest.getDate());
        System.out.println(searchRequest.getMinPrice());
        System.out.println(searchRequest.getMaxPrice());
        System.out.println(searchRequest.getSortBy());
        System.out.println(searchRequest.isInstantBook());
        System.out.println(searchRequest.getLimit());

              
        System.out.println(searchRequest.getRow());

        searchRequest.getProductTypes().forEach((s) -> {
            System.out.println(s);
        });

        String state = searchRequest.getLocation().substring(searchRequest.getLocation().length() - 2);
        String city = searchRequest.getLocation().substring(0, searchRequest.getLocation().length() - 4);
        List<Product> foundProducts = new ArrayList();
        city = city.replaceAll("_", " ");
        System.out.println(city);
        System.out.println(state);
   
        if(searchRequest.getSortBy().equalsIgnoreCase("Newest")) {
              foundProducts = productRepository.findProductsBySearchFiltersSortedByCreatedOn(city, state ,searchRequest.isInstantBook(),
                searchRequest.getRow(), 20);
        } else if (searchRequest.getSortBy().equalsIgnoreCase("Price: Low to High")) {
            foundProducts = productRepository.findProductsBySearchFiltersPriceAsc(city, state ,searchRequest.isInstantBook(), 
                searchRequest.getRow(), 20);
        } else if (searchRequest.getSortBy().equalsIgnoreCase("Price: High to Low") ) {
            foundProducts = productRepository.findProductsBySearchFiltersSortedByPriceDesc(city, state, searchRequest.isInstantBook(),
                searchRequest.getRow(), 20);
        } else {
             foundProducts = productRepository.findProductsBySearchFilters(city, state, searchRequest.isInstantBook(),
                searchRequest.getRow(), 20 );
        }
       
        

        for (int i = 0; i < foundProducts.size(); i++) {
            System.out.println("ProductId: " + foundProducts.get(i).getProductId());
            List<ProductReview> pr = productReviewRepository.findByProductId(foundProducts.get(i).getProductId());
            foundProducts.get(i).setProductReviews(pr);
        }

        System.out.println("Maybe we can make this happen!");
        return foundProducts;
    }

    @GetMapping("/resource/locations")
    public List<String> getAllActiveLocations() {
        return locationRepository.getAllActiveLocations();
    }

    @GetMapping("/resource/location={location}")
    public List<String> getLocationsByCity(@PathVariable String location) {
        return locationRepository.getLocationsByCity(location);
    }

    @GetMapping("/resource/locations={location}")
    public List<SimpleLocation> getLocationsByUserInput(@PathVariable String location) {
        String[] splitLocation = location.split("\\,+");
        String city = splitLocation[0].replace(",", "").trim();
        String state = "";
        if (splitLocation.length > 1) {
            state = splitLocation[1].trim();
        }

        List<SimpleLocation> results = simpleLocationRepository.findByInputSearch(city, state);
        return results;
    }

    @GetMapping("/resource/all-locations")
    public List<String> getAllLocations() {
        return locationRepository.getAllLocations();
    }

    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable Long productId) {

        Optional<Product> product = productRepository.findById(productId);
        Product foundProduct = product.get();
        foundProduct.setProductReviews(productReviewRepository.findByProductId(foundProduct.getProductId()));
        return foundProduct;
    }

}
