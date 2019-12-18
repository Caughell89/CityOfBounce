package com.example.springsocial.controller;

import com.example.springsocial.model.Company;
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
    
    @GetMapping("/products/search={searchString}")
    public List<Product> getProductsBySearchString(@PathVariable String searchString) {
        System.out.println(searchString);
            System.out.println(searchString.substring(searchString.lastIndexOf("=") + 1));

        List<Product> foundProducts = new ArrayList();
        foundProducts = productRepository.getProductsBySearchString(searchString.substring(searchString.lastIndexOf("=") + 1));

        return foundProducts;
    }
    
}
