package com.example.springsocial.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;


@Entity
@Table(name = "companies")
public class Company {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @Column(nullable = false)
    private String companyName;
    
    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String stateAbbr;
    
    @Column(nullable = false)
    private String companyUrl;

    @Column
    private String companyLogo;
    
    @CreationTimestamp
    @Column
    private LocalDateTime createdOn;
    
    @ElementCollection
    @CollectionTable(name="pending_employees", joinColumns=@JoinColumn(name="company_id"))
    @Column(name="email")
    private List<String> pendingEmployees = new ArrayList<>();
    
    
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="companies_areas",joinColumns=@JoinColumn(name="company_id"),inverseJoinColumns=@JoinColumn(name="zip_id"))
    private List<Location> areas = new ArrayList<>();
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="company_id")
    private List<Employee> employees = new ArrayList<>();
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="company_id")
    private List<Product> products = new ArrayList<>();
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="company_id")
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

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public List<Location> getAreas() {
        return areas;
    }

    public void setAreas(List<Location> areas) {
        this.areas = areas;
    } 

    public List<String> getPendingEmployees() {
        return pendingEmployees;
    }

    public void setPendingEmployees(List<String> pendingEmployees) {
        this.pendingEmployees = pendingEmployees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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
