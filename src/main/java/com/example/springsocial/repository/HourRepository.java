package com.example.springsocial.repository;

import com.example.springsocial.model.Hours;
import org.springframework.data.jpa.repository.JpaRepository;



public interface HourRepository extends JpaRepository<Hours, Long> {

    
    
}
