package com.example.springsocial.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="convo_users")
public class ConvoUser implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "convo_id")
    private Convo convo;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_viewed")
    private Date lastViewed;

    public Convo getConvo() {
        return convo;
    }

    public void setConvo(Convo convo) {
        this.convo = convo;
    }

    public Date getLastViewed() {
        return lastViewed;
    }

    public void setLastViewed(Date lastViewed) {
        this.lastViewed = lastViewed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
