package com.example.springsocial.payload;

import java.util.List;

public class SearchRequest {
    
    private String location;
    private String date;
    private double minPrice;
    private double maxPrice;
    private List <String>  productTypes;
    private boolean instantBook;
    private String sortBy;
    private int limit;
    private int row;
    

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List <String> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(List <String> productTypes) {
        this.productTypes = productTypes;
    }

    public boolean isInstantBook() {
        return instantBook;
    }

    public void setInstantBook(boolean instantBook) {
        this.instantBook = instantBook;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
    
}
