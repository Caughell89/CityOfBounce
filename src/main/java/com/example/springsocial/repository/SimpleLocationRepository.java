package com.example.springsocial.repository;

import com.example.springsocial.model.SimpleLocation;
import java.util.List;


public interface SimpleLocationRepository  {
    
    List<SimpleLocation> findByInputSearch(String city, String state);

    
}
