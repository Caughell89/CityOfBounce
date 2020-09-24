package com.example.springsocial.controller;

import com.example.springsocial.email.EmailTemplates;
import com.example.springsocial.model.Order;
import com.example.springsocial.repository.CompanyRepository;
import com.example.springsocial.repository.OrderRepository;
import com.example.springsocial.repository.ProductRepository;
import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;
    
    @Autowired
    private EmailTemplates emailTemplates;

    @Autowired
    private OrderRepository orderRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    
    @Autowired Scheduler scheduler;

    @RequestMapping(value = "/resource/Book", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Order bookParty(@RequestBody Order order) throws MessagingException, AddressException, IOException {
        System.out.println("We are booking a party here man!");

        Order sOrder = orderRepository.save(order);
        System.out.println(sOrder.getOrderId());
        System.out.println("WTF where is the order number");
        System.out.println("Sending email");
       
        emailTemplates.sendOrderConfirmation(sOrder, "Caughell89@yahoo.com");
//        for (int i = 0; i < order.getOrderProducts().size(); i++) {
//            OrderProducts op = new OrderProducts();
//            
//            op.setProductId(1);
//            op.setPrice(100.50);
//            op.setQuantity(1);
//            orderRepository.saveOrderProducts(sOrder.getOrderId(), op.getProductId(), op.getPrice(), op.getQuantity());
//        }
        
        
       
        return sOrder;
    }
    
    @GetMapping(value = "/user/orders/{userId}")
    @PreAuthorize("hasRole('USER')")
    public List<Order> bookParty(@PathVariable Long userId) {
        List<Order> foundOrders = orderRepository.getOrdersByUserId(userId);
        
        for(int i = 0; i < foundOrders.size();i++){
        }
        return foundOrders;
    }




}
