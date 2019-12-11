package com.example.springsocial.email;

import com.example.springsocial.model.Company;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailTemplates {

    public void sendmail(Company company, String email) throws AddressException, MessagingException, IOException {
        System.out.println(company.getCompanyName());
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("cityofbounce@gmail.com", "=Heat4044");

            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("info@cityofbounce.com", "City Of Bounce"));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        msg.setSubject(company.getCompanyName() + " wants you to join CityOfBounce");

        msg.setContent("<html lang='en'><head><meta http-equiv='Content-Type content='text/html; charset=UTF-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1'>"
                + "<meta http-equiv='X-UA-Compatible' content='IE=edge'><title></title><style type='text/css'></style></head>"
                + "<body style='margin:0; padding:20px; background-color:#ffffff; color:#6d6d6d;'>"
                + "<center><table width=''100% border='0' cellpadding='0' cellspacing='0' bgcolor='#ffffff'>"
                + "<tr><td style='border-bottom: 1px solid #cccccc'><img style='width:20%; height:auto; margin-bottom: 10px;' src='https://res.cloudinary.com/city-of-bounce/image/upload/v1558473737/LogoTealText.png' alt='City Of Bounce Logo'/></td></tr>"
                + "<tr><td style='padding-top:12px;'><h2>Let's get to work!</h2></td></tr>"
                + "<tr><td style='padding-bottom:12px;'>  " + company.getCompanyName() + " wants you to join CityOfBounce!</td></tr>"
                + "<tr><td >In order to get started, click the link to register.</td></tr>"
                + "</table>"
                + "<br>"
                + "<table style='text-align:center;' align=center>"
                + "<tr><td><h2>" + company.getCompanyName() + "</h2></td></tr>"
                + "<tr><td><img style='height:100px;width:auto;border-radius:50%;' src='" + company.getCompanyLogo() + " 'alt='Company Logo'/></td></tr>"
                + "<tr><td style='font-weight:bold; padding-top:12px'>" + company.getLocation() + ", " + company.getStateAbbr() + "</td></tr>"
                + "<tr><td text-align:center;'><a style='text-decoration:none; color:white;' href='http://localhost:3000/CompanyRegistration/" + email + "/" + company.getCompanyUrl() + "'><div style='margin: 12px;padding: 12px;color:white; font-weight:bold; background-color:#1cacc8; font-size:14px; text-align:center; cursor:pointer; border-radius:1px;'>REGISTER<div></a></td></tr>"
                + "</table>"
                + "<br>"
                + "<table style='padding-bottom:24px' align=left>"
                + "<tr><td style='padding-bottom:12px;'>Thanks,</td></tr>"
                + "<tr><td>The City Of Bounce Team</td></tr>"
                + "</table></center></body></html>", "text/html");

        Transport.send(msg);
        System.out.println("Email sent!");
    }

    public void sendEmailVerification(String email, String firstName) throws AddressException, MessagingException, IOException {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("cityofbounce@gmail.com", "=Heat4044");

            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("info@cityofbounce.com", "City Of Bounce"));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        msg.setSubject("Please confirm your email address - CityOfBounce");
        msg.setContent("<html lang='en'><head><meta http-equiv='Content-Type content='text/html; charset=UTF-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1'>"
                + "<meta http-equiv='X-UA-Compatible' content='IE=edge'><title></title><style type='text/css'></style></head>"
                + "<body style='margin:0; padding:0; background-color:#F2F2F2;'>"
                + "<center><br><br><table width=''100% border='0' cellpadding='0' cellspacing='0' bgcolor='#F2F2F2'>"
                + "<tr><td><img style='width:40%; height:auto' src='https://res.cloudinary.com/city-of-bounce/image/upload/v1558473737/LogoTealText.png' alt='City Of Bounce Logo'/></td></tr>"
                + "<tr><td>Hi, " + firstName + "</td></tr>"
                + "<tr><td>Welcome to the City Of Bounce! In order to get started, you need to confirm your email address.</td></tr>"
                + "<tr><td><a href='localhost:3000/ConfirmEmail'><div style={'color:white; font-weight:bold; background-color:#1cacc8; border-radius:5px;margin:4px 2px; font-size:16px; text-align:center; cursor:pointer'>Confirm Email</button></a></td></tr>"
                + "<tr><td>Thanks,</td></tr>"
                + "<tr><td>The City Of Bounce Team</td></tr>"
                + "</table></center></body></html>", "text/html");

        Transport.send(msg);
        System.out.println("Email sent!");
    }

}
