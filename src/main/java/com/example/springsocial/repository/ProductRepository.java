package com.example.springsocial.repository;

import com.example.springsocial.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    
    
    public static final String FIND_PRODUCTS = "SELECT * FROM products p JOIN companies c ON p.company_id=c.id JOIN companies_areas ca ON c.id=ca.company_id WHERE zip_id=?;";

    @Query(value = FIND_PRODUCTS, nativeQuery = true)
    public List<Product> getProductsBySearchString(String searchString);
}
