package com.example.springsocial.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "locations")

public class Location {
    
    @Id
    @Column(name="zipcode_id")
    private int zipcodeId;
    
    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String place;
    
    @Column(nullable = false)
    private String state;
    
    @Column(nullable = false)
    private String stateAbbr;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateAbbr() {
        return stateAbbr;
    }

    public void setStateAbbr(String stateAbbr) {
        this.stateAbbr = stateAbbr;
    }

    public int getZipcodeId() {
        return zipcodeId;
    }

    public void setZipcodeId(int zipcodeId) {
        this.zipcodeId = zipcodeId;
    }

}

