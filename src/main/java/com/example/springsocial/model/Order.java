package com.example.springsocial.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    Long orderId;

//    List<Long> companyIds;
//
//    List<Long> productIds;
    
    String customerName;
    
    String companyName;
    
    @Column(nullable=false)
    private String phone;

    String address1;

    String address2;

    String city;

    String state;

    String zipcode;
    
    @CreationTimestamp
    @Column
    private LocalDateTime createdOn;

    @Column(nullable = true, name = "special_instructions", columnDefinition = "TEXT")
    private String description;

}
