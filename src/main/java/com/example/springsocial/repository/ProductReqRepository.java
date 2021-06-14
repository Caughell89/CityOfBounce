package com.example.springsocial.repository;

import com.example.springsocial.model.ProductReq;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReqRepository extends JpaRepository<ProductReq, Long> {

    public List<ProductReq> findByProductId(Long productId);
    
}
