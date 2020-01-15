package com.example.springsocial.controller;

import com.example.springsocial.model.Company;
import com.example.springsocial.model.Location;
import com.example.springsocial.model.Product;
import com.example.springsocial.repository.CompanyRepository;
import com.example.springsocial.repository.LocationRepository;
import com.example.springsocial.repository.ProductRepository;
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
    
//    @GetMapping("/companies/{zipcode}")
//    public List<Company> getCompanysByZip(@PathVariable String zipcode) {
//        
//       
//
//        return foundCompany;
//    }
    
    @GetMapping("/products/search/{location}")
    public List<Product> getProductsBySearchString(@PathVariable String location) {
        String state = location.substring(location.length()-2);
        String city = location.substring(0, location.length()-3);
        List<Product> foundProducts = new ArrayList();
        city = city.replaceAll("_", " ");
        System.out.println("Searching for products in "+city + ", " +state);
        System.out.println(city);
        System.out.println(state);
        foundProducts = productRepository.getProductsBySearchString(city, state);
        
        return foundProducts;
    }
    
    @GetMapping("/resource/locations")
    public List<String> getAllLocations() {        
        return locationRepository.getAllLocations();
    }
    
    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        
        Optional<Product> product = productRepository.findById(productId);
        Product foundProduct = product.get();

        return foundProduct;
    }
    
}
