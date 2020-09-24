package com.example.springsocial.controller;

import com.example.springsocial.model.Company;
import com.example.springsocial.model.Location;
import com.example.springsocial.model.Product;
import com.example.springsocial.model.ProductReview;
import com.example.springsocial.repository.CompanyRepository;
import com.example.springsocial.repository.LocationRepository;
import com.example.springsocial.repository.ProductRepository;
import com.example.springsocial.repository.ProductReviewRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
    
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;
    
    @Autowired
    private ProductReviewRepository productReviewRepository;
    
    
    @GetMapping("/products/search/{location}")
    public List<Product> getProductsBySearchString(@PathVariable String location) {
        String state = location.substring(location.length()-2);
        String city = location.substring(0, location.length()-3);
        List<Product> foundProducts = new ArrayList();
        city = city.replaceAll("_", " ");

        foundProducts = productRepository.getProductsBySearchString(city, state);
        
        for (int i = 0; i<foundProducts.size();i++) {
            System.out.println("ProductId: " +foundProducts.get(i).getProductId());
            List<ProductReview> pr = productReviewRepository.findByProductId(foundProducts.get(i).getProductId());
            foundProducts.get(i).setProductReviews(pr);
        }
        
    
        return foundProducts;
    }
    
    @GetMapping("/products/search/{location}/Guests={guests}/Price=min={minPrice}&max={maxPrice}/Type=bhs={bhs}&tents={tents}&chairs={chairs}&tables={tables}&others={others}/InstantBook={instantBook}")
    public List<Product> getProductsBySearchFilters(@PathVariable String location, 
            @PathVariable Double minPrice, @PathVariable Double maxPrice, 
            @PathVariable boolean bhs, @PathVariable boolean tents, 
            @PathVariable boolean chairs,
            @PathVariable boolean tables, @PathVariable boolean others, 
            @PathVariable boolean instantBook) {
        String state = location.substring(location.length()-2);
        String city = location.substring(0, location.length()-4);
        List<Product> foundProducts = new ArrayList();
        city = city.replaceAll("_", " ");
        System.out.println("You are here");
        System.out.println(city+" "+state);
        System.out.println(minPrice);
        System.out.println(maxPrice);
        foundProducts = productRepository.findProductsBySearchFilters(city, state, minPrice, maxPrice);
        
        for (int i = 0; i<foundProducts.size();i++) {
            System.out.println("ProductId: " +foundProducts.get(i).getProductId());
            List<ProductReview> pr = productReviewRepository.findByProductId(foundProducts.get(i).getProductId());
            foundProducts.get(i).setProductReviews(pr);
        }
        
    
        return foundProducts;
    }
    
    @GetMapping("/resource/locations")
    public List<String> getAllActiveLocations() {        
        return locationRepository.getAllActiveLocations();
    }
    
    @GetMapping("/resource/locations={location}")
    public List<String> getLocationsByCity(@PathVariable String location) {  
        return locationRepository.getLocationsByCity(location);
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
