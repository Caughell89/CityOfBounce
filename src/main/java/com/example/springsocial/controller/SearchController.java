package com.example.springsocial.controller;

import com.example.springsocial.model.Product;
import com.example.springsocial.model.ProductReview;
import com.example.springsocial.model.SearchResult;
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

    @CrossOrigin    
    @GetMapping("/products/search/{location}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public SearchResult getProductsBySearchString(@PathVariable String location) {
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
        System.out.println("Print city " + city);
        System.out.println("Print state " + state);
        SearchResult sr = new SearchResult();
        sr.setProducts(foundProducts);
        sr.setCount(productRepository.getAllProductCount(city, state));
        sr.setMaxPrice(productRepository.getAllMaxPrice(city, state));

        return sr;
    }

    @CrossOrigin
    @PostMapping("/products/search")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public SearchResult getProductsBySearchFilters(@RequestBody SearchRequest searchRequest) {
        
        System.out.println(searchRequest.getMinPrice());
        System.out.println(searchRequest.getMaxPrice());

        searchRequest.getProductTypes().forEach((s) -> {
            System.out.println(s);
        });
       
        String state = searchRequest.getLocation().substring(searchRequest.getLocation().length() - 2);
        String city = searchRequest.getLocation().substring(0, searchRequest.getLocation().length() - 4);

        city = city.replaceAll("_", " ");
       
        
        List<Product> foundProducts = new ArrayList();
        SearchResult sr = new SearchResult();
        
        sr.setCount(100);
        sr.setMaxPrice(500.99);
        if(searchRequest.getSortBy().equalsIgnoreCase("Newest")) {
              foundProducts = productRepository.findProductsBySearchFiltersSortedByCreatedOn(searchRequest.getProductTypes(), city, state,searchRequest.isInstantBook(),
                searchRequest.getMinPrice(), searchRequest.getMaxPrice(), searchRequest.getRow(), 20);
              System.out.println("Setting count");
              sr.setCount(productRepository.getProductCount(searchRequest.getProductTypes(), 
                      city, state, searchRequest.getMinPrice(), searchRequest.getMaxPrice()
                      ));
              System.out.println(sr.getCount());
        } else if (searchRequest.getSortBy().equalsIgnoreCase("Price: Low to High")) {
            foundProducts = productRepository.findProductsBySearchFiltersPriceAsc(
                    searchRequest.getProductTypes(), city, state,
                    searchRequest.isInstantBook(),  searchRequest.getMinPrice(), 
                    searchRequest.getMaxPrice(), searchRequest.getRow(), 20);
             System.out.println("Setting count");
              sr.setCount(productRepository.getProductCount(
                      searchRequest.getProductTypes(), city, state, 
                      searchRequest.getMinPrice(), searchRequest.getMaxPrice()));
              System.out.println(sr.getCount());
        } else if (searchRequest.getSortBy().equalsIgnoreCase("Price: High to Low") ) {
            foundProducts = productRepository.findProductsBySearchFiltersSortedByPriceDesc(
                    searchRequest.getProductTypes(),city, state, searchRequest.isInstantBook(),
                searchRequest.getMinPrice(), searchRequest.getMaxPrice(),
                     searchRequest.getRow(), 20);
           
             System.out.println("Setting count");
              sr.setCount(productRepository.getProductCount(
                      searchRequest.getProductTypes(), city, state,
                      searchRequest.getMinPrice(), searchRequest.getMaxPrice()));
              System.out.println(sr.getCount());
        } else {
             foundProducts = productRepository.findProductsBySearchFilters(
                     searchRequest.getProductTypes(),city, state, searchRequest.isInstantBook(),
                     searchRequest.getMinPrice(), searchRequest.getMaxPrice(),
                     searchRequest.getRow(), 20);
              System.out.println("Setting count");
              sr.setCount(productRepository.getProductCount(
                      searchRequest.getProductTypes(), city, state,
                      searchRequest.getMinPrice(), searchRequest.getMaxPrice()));
              System.out.println(sr.getCount());
        }
       
        

        for (int i = 0; i < foundProducts.size(); i++) {
            System.out.println("ProductId: " + foundProducts.get(i).getProductId());
            List<ProductReview> pr = productReviewRepository.findByProductId(foundProducts.get(i).getProductId());
            foundProducts.get(i).setProductReviews(pr);
        }
        
        sr.setProducts(foundProducts);

        System.out.println("Maybe we can make this happen!");
        return sr;
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
