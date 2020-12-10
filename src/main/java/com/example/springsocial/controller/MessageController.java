package com.example.springsocial.controller;

import com.example.springsocial.model.Company;
import com.example.springsocial.model.CompanyMessage;
import com.example.springsocial.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class MessageController {
    
    @Autowired MessageRepository messageRepository;

    @RequestMapping(value = "/resource/message/{messageId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void markAsRead(@PathVariable Long messageId){
        
        messageRepository.setIsRead(messageId);

    }
    
    @CrossOrigin
    @RequestMapping(value = "/resource/message/send/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CompanyMessage sendMessage(@RequestBody CompanyMessage message){
        
        CompanyMessage savedMessage = messageRepository.save(message);
        return savedMessage;
        
    }
    
    @RequestMapping(value = "/resource/message/delete/{messageId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void flagDeleted(@PathVariable Long messageId){
        
        messageRepository.setIsDeleted(messageId);
  
    }
    
    @RequestMapping(value = "/resource/message/move/{messageId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void flagNotDeleted(@PathVariable Long messageId){
        
        messageRepository.setNotDeleted(messageId);
  
    }
    
}
