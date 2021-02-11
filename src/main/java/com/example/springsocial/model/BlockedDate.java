package com.example.springsocial.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "company_blocked_dates")
public class BlockedDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blockedDateId;
    
    private String blockedDate;
      
    public Long getBlockedDateId() {
        return blockedDateId;
    }

    public void setBlockedDateId(Long blockedDateId) {
        this.blockedDateId = blockedDateId;
    }

    public String getBlockedDate() {
        return blockedDate;
    }

    public void setBlockedDate(String blockedDate) {
        this.blockedDate = blockedDate;
    }
  
}
