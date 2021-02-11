package com.example.springsocial.util;

import com.example.springsocial.email.EmailTemplates;
import com.example.springsocial.model.Order;
import com.example.springsocial.repository.MessageRepository;
import com.example.springsocial.repository.OrderRepository;
import com.example.springsocial.repository.ProductRepository;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "sceduing.enabled", matchIfMissing = true)
public class ScheduledTasks {

    @Autowired
    private EmailTemplates emailTemplates;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private MessageRepository messageRepository;

    @Scheduled(cron = "0 5 20 * * *")
    void sendTodaysCustomerOrders() throws MessagingException, AddressException, IOException {
        System.out.println("Now is " + new Date());

        //String date = dateFormat.format(new Date());
        LocalDateTime date = LocalDateTime.now();
        System.out.println("Formatted date is " + date.withHour(0).withMinute(0).withSecond(0).withNano(0));

        ArrayList<Order> foundOrders = orderRepository.findByEventDate(date.withHour(0).withMinute(0).withSecond(0).withNano(0));
        System.out.println(foundOrders.size());
        for (int i = 0; i < foundOrders.size(); i++) {
            System.out.println(foundOrders.get(i).getOrderProducts().size());
            for (int j = 0; j < foundOrders.get(i).getOrderProducts().size(); j++) {
                System.out.println(foundOrders.get(i).getOrderProducts().get(j).getPrice());

            }
            emailTemplates.sendDayOfOrderEmail(foundOrders.get(i));
        }
        System.out.println("All Emails Sent!");

    }

    @Scheduled(cron = "0 20 15 * * *")
    void someJob2() throws MessagingException, IOException {
        System.out.println("Now is " + new Date());
        emailTemplates.sendWelcomeEmail();
    }
    
     @Scheduled(cron = "0 29 * * * *")
    void keepDynamosAlive() {
        int orderCount = orderRepository.getOrderCount();
        //Send an Email?
        System.out.println("Now is " + new Date());

        System.out.println("New orders for the day!");
        System.out.println(orderCount);
    }
}
