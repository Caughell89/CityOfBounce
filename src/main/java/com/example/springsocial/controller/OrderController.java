package com.example.springsocial.controller;

import com.example.springsocial.email.EmailTemplates;
import com.example.springsocial.model.Order;
import com.example.springsocial.model.OrderProducts;
import com.example.springsocial.model.Product;
import com.example.springsocial.payload.OrderProductsRequest;
import com.example.springsocial.payload.OrderRequest;
import com.example.springsocial.repository.CompanyRepository;
import com.example.springsocial.repository.OrderRepository;
import com.example.springsocial.repository.ProductRepository;
import java.io.IOException;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    
    @CrossOrigin
    @RequestMapping(value = "/resource/Book", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Order bookParty(@RequestBody OrderRequest orderRequest) throws MessagingException, AddressException, IOException {
        System.out.println("We are booking a party here man!");
        System.out.println(orderRequest.getOrderProducts().size());
        Order order = mapOrderRequestToOrder(orderRequest);

        Order sOrder = orderRepository.save(order);
        System.out.println(sOrder.getOrderId());

        emailTemplates.sendOrderConfirmation(sOrder, "Caughell89@yahoo.com");

        return sOrder;
    }
    
    @GetMapping(value = "/user/orders/{userId}")
    @PreAuthorize("hasRole('USER')")
    public List<Order> bookParty(@PathVariable Long userId) {
        List<Order> foundOrders = orderRepository.getOrdersByUserId(userId);
        
        System.out.println("Finding orders");
        System.out.println(foundOrders.size());
        return foundOrders;
    }
    
    @GetMapping(value = "resource/blockedDates/{productId}")
    @ResponseBody
    public ArrayList<String> getBlockedDates(@PathVariable Long productId) {
        
        return orderRepository.getBlockedDates(productId);
    }
    
    private Order mapOrderRequestToOrder(OrderRequest orderRequest) {
        Order order = new Order();
        List<OrderProducts> orderProducts = new ArrayList<>();
        order.setUserId(orderRequest.getUserId());
        order.setCustomerName(orderRequest.getCustomerName());
        order.setEmail(orderRequest.getEmail());
        order.setPhone(orderRequest.getPhone());
        
        order.setAddress(orderRequest.getAddress());
        order.setCity(orderRequest.getCity());
        order.setState(orderRequest.getState());
        order.setZipcode(orderRequest.getZipcode());
        
        order.setEventDate(orderRequest.getEventDate());
        order.setDescription(orderRequest.getDescription());
        
        for (OrderProductsRequest orderProductRequest : orderRequest.getOrderProducts()) {
            OrderProducts lineItem = new OrderProducts();
            lineItem.setPrice(orderProductRequest.getPrice());
            lineItem.setQuantity(orderProductRequest.getQuantity());
            lineItem.setTaxRate(orderProductRequest.getTaxRate());
            lineItem.setSalesTax(orderProductRequest.getSalesTax());
            
            Optional<Product> product = productRepository.findById(orderProductRequest.getProductId());
            Product foundProduct = product.get();
            
            lineItem.setProduct(foundProduct);
            orderProducts.add(lineItem);
        }
        
        order.setOrderProducts(orderProducts);
                
        return order;
    }
}
