package com.example.springsocial.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "companies")
public class Company {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;
    
    @Column(nullable= false)
    private String paymentId;

    @Column(nullable = false)
    private String companyName;
    
    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String stateAbbr;
    
    @Column(nullable = false)
    private String companyUrl;

    @Column(nullable = false,
            columnDefinition="varchar(255) default 'https://res.cloudinary.com/city-of-bounce/image/upload/v1603887970/DefaultCompanyLogo.jpg'") 
    private String companyLogo;
    
    @CreationTimestamp
    @Column
    private LocalDateTime createdOn;
    
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
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="company_id")
    private List<CompanyMessage> companyMessages = new ArrayList<>();
    
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="company_id")
    @OrderBy("blocked_date asc")
    private List<BlockedDate> blockedDates = new ArrayList<>();
 
    
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

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public List<CompanyMessage> getCompanyMessages() {
        return companyMessages;
    }

    public void setCompanyMessages(List<CompanyMessage> companyMessages) {
        this.companyMessages = companyMessages;
    }

    public List<BlockedDate> getBlockedDates() {
        return blockedDates;
    }

    public void setBlockedDates(List<BlockedDate> blockedDates) {
        this.blockedDates = blockedDates;
    }
    
}
