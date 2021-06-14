package com.example.springsocial.repository;

import com.example.springsocial.model.ProductFeature;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductFeatureRepository extends JpaRepository<ProductFeature, Long> {

    public List<ProductFeature> findByProductId(Long productId);
    
}
