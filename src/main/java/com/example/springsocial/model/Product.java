package com.example.springsocial.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @Column(nullable = false, name="company_id")
    private Long companyId;
    
    @Column(nullable = false, name="product_name")
    private String productName;
    
    @Column(nullable = false, name="product_type")
    private String productType;
   
    @Column(nullable = false, name="price")
    private Float price;
    
    @Column(nullable=false, name="quantity")
    private int quantity;
    
    @Column(nullable = true , name="capacity")
    private int capacity;
    
    @Column(nullable = true, name="length")
    private Float length; 
    
    @Column(nullable = true, name="width")
    private Float width;
    
    @Column(nullable = false, name="color")
    private String color;
    
    @Column(nullable = true, name="material")
    private String material;
    
    @Column(nullable = false, name="setup_time")
    private int setupTime;
    
    @Column(nullable = true, name="product_description", columnDefinition="TEXT")
    private String description;
    
    @ElementCollection
    @CollectionTable(name = "product_photos")
    private List<String> productPhotos;    
    
    @OneToMany
    @JoinColumn(name="product_id")
    private List<ProductReview> productReviews = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public List<String> getProductPhotos() {
        return productPhotos;
    }

    public void setProductPhotos(List<String> productPhotos) {
        this.productPhotos = productPhotos;
    }

    public List<ProductReview> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(List<ProductReview> productReviews) {
        this.productReviews = productReviews;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(int setupTime) {
        this.setupTime = setupTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
