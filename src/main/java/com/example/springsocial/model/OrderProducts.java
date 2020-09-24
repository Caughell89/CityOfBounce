package com.example.springsocial.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "order_products")
public class OrderProducts implements Serializable  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OrderProductId;
    
    @Column(name = "order_id")
    private Long orderId;
    
    @Column(nullable = false, name = "product_id")
    private int productId;
    
    @Column(nullable = false, name = "quantity")
    private int quantity;
        
    @Column(nullable = false, name = "price")
    private double price;
    
    @ElementCollection   
    @CollectionTable(
        name = "product_photos",
        joinColumns=@JoinColumn(name = "product_id", referencedColumnName = "product_id")
    )
    private List<String> productPhotos;  

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Long getOrderProductId() {
        return OrderProductId;
    }

    public void setOrderProductId(Long OrderProductId) {
        this.OrderProductId = OrderProductId;
    }

    public List<String> getProductPhotos() {
        return productPhotos;
    }

    public void setProductPhotos(List<String> productPhotos) {
        this.productPhotos = productPhotos;
    }
        
    
}
