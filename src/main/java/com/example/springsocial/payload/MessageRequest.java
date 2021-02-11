package com.example.springsocial.payload;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

public class MessageRequest {
    
    private Long convoId;
    
    private Long senderId;
    
    @NotNull
    private String sender;
    
    private String img;
    
    @NotNull
    private String message;
    
    @NotNull
    private String sentAt;

    public Long getConvoId() {
        return convoId;
    }

    public void setConvoId(Long convoId) {
        this.convoId = convoId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentAt() {
        return sentAt;
    }

    public void setSentAt(String sentAt) {
        this.sentAt = sentAt;
    }
            
}
