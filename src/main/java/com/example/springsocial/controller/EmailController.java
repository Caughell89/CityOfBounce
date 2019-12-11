package com.example.springsocial.controller;

import com.example.springsocial.email.EmailTemplates;
import com.example.springsocial.model.Company;
import com.example.springsocial.payload.SignUpRequest;
import com.example.springsocial.repository.CompanyRepository;
import java.io.IOException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    
    @Autowired
    private EmailTemplates emailTemplates;
    
    @Autowired
    private CompanyRepository companyRepository;

    @RequestMapping(value = "{email}/sendRegistrationEmail")
    public Company sendEmail(@PathVariable String email, @RequestBody Company company) throws AddressException, MessagingException, IOException {
        System.out.println("Sending email");
        companyRepository.linkPendingEmployeeWithCompany(company.getId(),email);
        emailTemplates.sendmail(company, email);
        return company;
    }
    
    @RequestMapping(value = "/sendVerificationEmail")
    public void sendVerficationEmail(@RequestBody SignUpRequest signUpRequest) throws AddressException, MessagingException, IOException {
        System.out.println("Sending email");
        emailTemplates.sendEmailVerification(signUpRequest.getEmail(), signUpRequest.getFirstName());
    }



}
