package com.example.springsocial.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_products")
public class OrderProducts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OrderProductId;

    @ManyToOne
    @JoinColumn(name="order_id")
    @JsonBackReference
    private Order order;
    
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @Column(nullable = false, name = "quantity")
    private int quantity;

    @Column(nullable = false, name = "price")
    private double price;
    
    @Column(nullable = false, name = "tax_rate")
    private double taxRate;
    
    @Column(nullable = false, name = "salesTax")
    private double salesTax;
    
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

    public Long getOrderProductId() {
        return OrderProductId;
    }

    public void setOrderProductId(Long OrderProductId) {
        this.OrderProductId = OrderProductId;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
