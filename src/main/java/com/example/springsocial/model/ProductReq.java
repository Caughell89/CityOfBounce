package com.example.springsocial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_reqs")
public class ProductReq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "req_id")
    private Long reqId;

    @Column(nullable = false, name = "product_id")
    private Long productId;

    @Column(nullable = false, name="req")
    private String req;

    public Long getReqId() {
        return reqId;
    }

    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }
    
}
