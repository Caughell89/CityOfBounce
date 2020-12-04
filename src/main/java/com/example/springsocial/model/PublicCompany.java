package com.example.springsocial.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class PublicCompany {
        

    private String companyName;
    
    private String location;

    private String stateAbbr;
    
    private String companyUrl;

    private String companyLogo;
    
    private LocalDateTime createdOn;
    
   private List<Location> areas = new ArrayList<>();
    
    private List<Product> products = new ArrayList<>();
    
    private List<Hours> hours = new ArrayList<>();

    

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStateAbbr() {
        return stateAbbr;
    }

    public void setStateAbbr(String stateAbbr) {
        this.stateAbbr = stateAbbr;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public List<Location> getAreas() {
        return areas;
    }

    public void setAreas(List<Location> areas) {
        this.areas = areas;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Hours> getHours() {
        return hours;
    }

    public void setHours(List<Hours> hours) {
        this.hours = hours;
    }
    
}
