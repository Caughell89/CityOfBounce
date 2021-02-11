package com.example.springsocial.controller;

import com.example.springsocial.model.Company;
import com.example.springsocial.model.CompanyMessage;
import com.example.springsocial.model.Convo;
import com.example.springsocial.model.ConvoUser;
import com.example.springsocial.model.Message;
import com.example.springsocial.payload.MessageRequest;
import com.example.springsocial.repository.ConvoRepository;
import com.example.springsocial.repository.ConvoUsersRepository;
import com.example.springsocial.repository.MessageRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class MessageController {

    @Autowired
    MessageRepository messageRepository;
    
    @Autowired
    ConvoRepository convoRepository;
    
    @Autowired
    ConvoUsersRepository convoUsersRepository;

    @RequestMapping(value = "/resource/message/{messageId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void markAsRead(@PathVariable Long messageId) {

        messageRepository.setIsRead(messageId);

    }

//    @CrossOrigin
//    @RequestMapping(value = "/resource/message/send/", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody
//    public CompanyMessage sendMessage(@RequestBody CompanyMessage message) {
//
//        CompanyMessage savedMessage = messageRepository.save(message);
//        return savedMessage;
//
//    }

    @CrossOrigin
    @RequestMapping(value = "/resource/message/send_v2/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Message sendMessage1(@RequestBody Message message) {

       
       System.out.println("Sending and persisting a new message");
    
        
        messageRepository.save(message);
        return message;
    }

    @RequestMapping(value = "/resource/message/delete/{messageId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void flagDeleted(@PathVariable Long messageId) {

        messageRepository.setIsDeleted(messageId);

    }

    @RequestMapping(value = "/resource/message/move/{messageId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void flagNotDeleted(@PathVariable Long messageId) {

        messageRepository.setNotDeleted(messageId);

    }
    

    @GetMapping("/resource/convos/{userId}")
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public List<ConvoUser> getConvos(@PathVariable Long userId) {
        System.out.println("Looking for convos");
        List<ConvoUser> cu = convoUsersRepository.findByUserId(userId);
        System.out.println(cu.size());
        System.out.println("So we got how many convos for the userId: " + userId);
      
        return cu;
    }

}
