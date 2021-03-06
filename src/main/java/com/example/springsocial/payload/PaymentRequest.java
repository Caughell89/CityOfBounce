package com.example.springsocial.payload;

import javax.validation.constraints.NotBlank;

public class PaymentRequest {
    
    @NotBlank
    private int price;
    
    @NotBlank
    private String stripeId;
    
    @NotBlank
    private int tax;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStripeId() {
        return stripeId;
    }

    public void setStripeId(String stripeId) {
        this.stripeId = stripeId;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }
}
