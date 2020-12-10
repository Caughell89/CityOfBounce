package com.example.springsocial.email;

import com.example.springsocial.model.Company;
import com.example.springsocial.model.Order;
import java.io.IOException;
import java.util.Calendar;
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

    int year = Calendar.getInstance().get(Calendar.YEAR);

    String emailT = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org//TR/xhtml1/DTD/xhtml1-transitional.dtd'>"
            + "<html xmlns='http://www.w3.org/1999/xhtml'>"
            + "<head>"
            + "<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>"
            + "<meta http-equiv='X-UA-Compatible' content='IE-edge'>"
            + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
            + "<meta name='format-detection' content='telephone=no>"
            + "<link rel='shortcut Icon' type='image/x-icon' href='https://res.cloudinary.com/city-of-bounce/image/upload/v1558480258/circleLogoTealText_mduay7.png' />"
            + "<title>Needs Dynamic title based on email being sent</title>"
            + "<style type='text/css'>"
            + "@media screen and (max-width: 600px) {"
            + "img.third-img-last {"
            + "width:200px;!important"
            + "max-width:200px;!important"
            + "}"
            + ".padding {"
            + "padding-right:0!important;"
            + "padding-left:0!important;"
            + "}"
            + "}"
            + "@media screen and (max-width: 400px) {"
            + "img.third-img {"
            + "width:200px;!important"
            + "max-width:200px;!important"
            + "}"
            + "}"
            + "</style>"
            + "</head>"
            + "<body style='margin:0;padding:0;background-color:#f6f9fc;'>"
            + "<center class='wrapper' style='width:100%;table-layout:fixed;background-color:#f6f9fc;padding-botton:40px;'>"
            + "<div class='webkit' style='max-width:600px;background-color:#ffffff;'>"
            + "<table class='outer' align='center' style='margin:0 auto;width:100%;max-width:600px;border-spacing:0;font-family: Circular, -apple-system, BlinkMacSystemFont, Roboto,'Helvetica Neue', sans-serif;"
            + "color:#4a4a4a;'>"
            + "<tr>"
            + "<td style='padding:0;'>"
            + "<table width='100%' style='border-spacing:0;border-spacing: 0;'>"
            + "<tr>"
            + "<td height='0' style='padding:0;background-color:#1cacc8;background:linear-gradient(45deg, #1cacc8 0%, #16889c 100%);'></td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:0;"
            + "background-color:#ffffff;"
            + "padding-left: 24px;'>"
            + "<a href='https://cityofbounce.com' target='_blank' style='text-decoration:none;color:blue;font-size:16px;'><img src='https://res.cloudinary.com/city-of-bounce/image/upload/v1558480258/circleLogoTealText_mduay7.png' alt='Logo' title='Logo' width='80' style='border:0;'></a>"
            + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td height='6' style='padding:0;background-color:#1cacc8;background:linear-gradient(45deg, #1cacc8 0%, #16889c 100%);'></td>"
            + "</tr>"
            + "</table>"
            + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:0;'>"
            + "<a href='#' style='text-decoration:none;color:blue;font-size:16px;'><img src='https://res.cloudinary.com/city-of-bounce/image/upload/v1604618932/EmailBanner.png' alt='Banner' width='600' style='border:0;max-width: 100%;'></a>"
            + "</td>"
            + "</tr>";

    String newFooter
            = "<tr>"
            + "<td style='padding:0;'>"
            + "<table width='100%' style='border-spacing:0;border-spacing: 0;margin:0;'>"
            + "<tr>"
            + "<td style='background-color:#1cacc8;"
            + "background:linear-gradient(45deg, #1cacc8 0%, #16889c 100%);"
            + "padding-bottom: 12px;"
            + "text-align: center;'>"
            + "<p style='"
            + "font-size: 14px;"
            + "font-weight: bold;"
            + "color: #ffffff;"
            + "margin-bottom: 13px;"
            + "'>"
            + "Connect with us"
            + "</p>"
            + "<a href='https://www.facebook.com/cityofbounce' style='text-decoration:none;font-size:16px;margin:8px; height:24px; width;auto;'><img src='https://res.cloudinary.com/city-of-bounce/image/upload/v1600184987/facebook.png' alt='facebook link'  style='border:0;'></a>"
            + "<a href='https://twitter.com/CityOfBounce' style='text-decoration:none;font-size:16px;margin:8px;height:24px; width;auto;'><img src='https://res.cloudinary.com/city-of-bounce/image/upload/v1600184952/twitter.png' alt='twitter link'  style='border:0;'></a>"
            + "<a href='https://www.instagram.com/cityofbounce' style='text-decoration:none;font-size:16px;margin:8px;height:24px; width;auto;'><img src='https://res.cloudinary.com/city-of-bounce/image/upload/v1600184974/instagram.png' alt='instagram link' style='border:0;'></a>"
            + "</td>"
            + "</tr>"
            + "</table>"
            + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:0;background-color: #efefef; border-spacing: 0;'>"
            + "<table width='100%' style='border-spacing:0;'>"
            + "<tr>"
            + "<td style='padding:0;"
            + "padding: 20px;"
            + "text-align: center;"
            + "padding-bottom: 10px;'>"
            + "<a href='#' style='text-decoration:none;color:blue;font-size:16px;'><img src='' alt='' width='160' style='border:0;'/></a>"
            + "<p style='"
            + "font-size: 10px;"
            + "margin-top: 4px;"
            + "margin-bottom: 0px;'>"
            + "&#xA9 " + year + " City of Bounce, Inc. All rights reserved"
            + "</p>"
            + "<p style='font-size: 10px; margin-bottom: 0px;'>"
            + "538 Richmond Ave. | Buffalo, NY"
            + "</p>"
            + "<p style='font-size: 12px; margin-bottom: 4px;'>"
            + "<a href='mailto:cityofbounce@gmail.com' style='text-decoration:none;color:#1cacc8;font-size:14px;cursor:pointer;'>cityofbounce@gmail.com</a>"
            + "</p>"
            + "<p style='font-size: 12px; margin-bottom: 4px;'>"
            + "<a href='tel:+1-716-200-3543' style='text-decoration:none;color:#1cacc8;font-size:12px;'>716-200-3543</a>"
            + "</p>"
            + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td align='center' style='padding:0;padding-bottom: 25px; text-align: center;'>"
            + "<p><a href='#' style='text-decoration:none;color:#b3b3b3;font-size:16px;font-size: 13px;'>UNSUBSCRIBE</a></p>"
            + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td height='8px' style='padding:0;background-color:#1cacc8;background:linear-gradient(45deg, #1cacc8 0%, #16889c 100%);'></td>"
            + "</tr>"
            + "</table>"
            + "</td>"
            + "</tr>"
            + "</table>"
            + "</div>"
            + "</center>"
            + "</body>"
            + "</html>";

    String footer = "<table style='color:#484848; padding:12px; background-color:#f2f2f2; width:100%; text-align:center;' align=center>"
            + "<tr><td style='padding-bottom:12px;'><a href=\"https://www.facebook.com/cityofbounce/\"\n"
            + "                alt=\"facebook-profile\"\n"
            + "                target=\"_blank\"\n"
            + "                rel=\"noopener noreferrer\"><img style='cursor:pointer; margin:8px;' src=\"https://res.cloudinary.com/city-of-bounce/image/upload/v1600184987/facebook-f-brands_du4fj1.png\" /></a><a href=\"https://twitter.com/CityOfBounce\"\n"
            + "                alt=\"twitter-profile\"\n"
            + "                target=\"_blank\"\n"
            + "                rel=\"noopener noreferrer\"><img style='cursor:pointer; margin:8px;' src=\"https://res.cloudinary.com/city-of-bounce/image/upload/v1600184952/twitter-brands_md5xe6.png\" /></a> <a href=\"https://www.instagram.com/cityofbounce/\"\n"
            + "                alt=\"instagram-profile\"\n"
            + "                target=\"_blank\"\n"
            + "                rel=\"noopener noreferrer\"><img style='cursor:pointer; margin:8px;' src=\"https://res.cloudinary.com/city-of-bounce/image/upload/v1600184974/instagram-brands_dbklix.png\" /></a></td></tr>"
            + "<tr><td style='font-size:12px;'>&copy " + year + " City of Bounce, Inc. All rights reserved</td></tr>"
            + "</table>";

    public void sendmail(Company company, String email) throws AddressException, MessagingException, IOException {
        System.out.println(company.getCompanyName());
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("cityofbounce@gmail.com", "aesqgdsbwydtozda");

            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("cityofbounce@gmail.com", "City Of Bounce"));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        msg.setSubject(company.getCompanyName() + " wants you to join CityOfBounce");

        msg.setContent(emailT, "text/html");

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
                return new PasswordAuthentication("cityofbounce@gmail.com", "aesqgdsbwydtozda");

            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("cityofbounce@gmail.com", "City Of Bounce"));

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

    public void sendOrderConfirmation(Order order, String email) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("cityofbounce@gmail.com", "aesqgdsbwydtozda");

            }
        });
        
        String productsHTML = "";
        
        for(int i = 0; i < order.getOrderProducts().size(); i++){
            System.out.println("How many products?");
            System.out.println(order.getOrderProducts().size());
            System.out.println(order.getOrderProducts().get(i).getPrice());
            System.out.println("Order Day");
            System.out.println(order.getEventDate());
            System.out.println("Order Date");
            System.out.println(order.getEventDate().getMonth().toString().substring(0,1)+order.getEventDate().getMonth().toString().toLowerCase().substring(1)+" " + order.getEventDate().getDayOfMonth() + " "+ order.getEventDate().getYear());

            System.out.println(order.getOrderProducts().get(i).getQuantity());
            System.out.println(order.getOrderProducts().get(i).getSalesTax());
            System.out.println(order.getOrderProducts().get(i).getOrderProductId());
            System.out.println(order.getOrderProducts().get(i).getProduct().getProductName());
            System.out.println(order.getOrderProducts().get(i).getProduct().getProductPhotos().get(0));
            
            
            
            
            productsHTML = productsHTML + "<table class='column' style='border-spacing:0;width:100%;max-width:200px;display:inline-block;vertical-align:top;'>"
                + "<tr>"
                + "<td class='padding' style='padding:0;padding:15px;'>"
                + "<table class='content' style='border-spacing:0;font-size:15px;line-height:20px;'>"
                + "<tr>"
                + "<td style='padding:0;'>"
                + "<a href='#' style='text-decoration:none;color:blue;font-size:16px;'><img src='"+order.getOrderProducts().get(i).getProduct().getProductPhotos().get(0)+"'' alt='product' width='150' style='border:0;max-width: 150px;' class='third-img'></a>"
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td style='padding:0;padding: 10px;'>"
                + "<p style='font-size: 17px; font-weight: bold;'>"
                + order.getOrderProducts().get(i).getProduct().getProductName()
                + "</p>"
                + "<p>"
                + "Amount: "+ order.getOrderProducts().get(i).getQuantity()
                + "</p>"
                + "<a href='#' style='text-decoration:none;color:blue;font-size:16px;'></a>"
                + "</td>"
                + "</tr>"
                + "</table>"
                + "</td>"
                + "</tr>"
                + "</table>";
            
            
            
            
            
            
        }
                
                
                
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("cityofbounce@gmail.com", "City Of Bounce"));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        msg.setSubject("Thanks for your order!");
        
        // Building the email body
        String body = "<tr>"
                + "<td style='padding:15px;'>"
                + "<table>"
                + "<tr>"
                + "<td style='padding-bottom:10px; font-weight:bold;'>Thanks for your order!    </td>"
                + "</tr>"
                 + "<tr>"
                + "<td style='padding-bottom:4px; font-weight:bold;'>Event Details:    </td>"
                + "</tr>"           
                 + "<tr>"
                + "<td>"+order.getCustomerName()+"</td>"
                + "</tr>"   
                 + "<tr>"
                + "<td>"+ order.getEventDate().getDayOfWeek().toString().substring(0,1)+order.getEventDate().getDayOfWeek().toString().toLowerCase().substring(1)+"</td>"
                + "</tr>"
                 + "<tr>"
                + "<td>"+order.getEventDate().getMonth().toString().substring(0,1)+order.getEventDate().getMonth().toString().toLowerCase().substring(1)+" " + order.getEventDate().getDayOfMonth() + " "+ order.getEventDate().getYear() +"</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"+order.getAddress()+"</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"+order.getCity()+", "+ order.getState()+ " " +order.getZipcode()+"</td>"
                + "</tr>"
                + "<tr>"
                + "<td style='padding-top:8px; padding-bottom:4px; font-weight:bold;'>Contact Info:</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"+order.getEmail()+"</td>"
                + "</tr>"
                + "<tr>"
                + "<td style='padding-top:4px;'>"+order.getPhone()+"</td>"
                + "</tr>"
                + "</table>"
                + "</td>"
                + "</tr>"          
                + "<tr>"
                + "<td style='padding:0;'>"
                + "<table width='100%' style='border-spacing:0;broder-spacing: 0;'>"
                + "<tr>"
                + "<td class='three-columns' style='padding:0;text-align:center;font-size:0;padding-top:40px;padding-bottom:30px;'>"
              
                + productsHTML
                + "</td>"
                + "</tr>"
                + "</table>"
                + "</td>"
                + "</tr>"
                + "<table width='100%' style='border-spacing:0;broder-spacing: 0;'>"
                + "<tr>"
                + "<td style='padding:0;text-align:center;font-size:8px;padding-top:10px;padding-bottom:10px;'>"            
                + "THIS EMAIL WAS SENT TO " + order.getEmail()
                + "</td>"
                + "</tr>"
                + "</table>";

        msg.setContent(emailT + body + newFooter, "text/html");
        Transport.send(msg);
        System.out.println("Email sent!");
    }

    public void sendDayOfOrderEmail(Order order) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("cityofbounce@gmail.com", "aesqgdsbwydtozda");

            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("cityofbounce@gmail.com", "City Of Bounce"));

        //Iterate through list of orders for the day
        // Email address is currently hardcoded needs to be fixed
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(order.getEmail()));
        msg.setSubject("Get Ready!  Your event is hours away!");

        // Building the email body
        String body = "<tr>"
                + "<td style='padding:15px;'>"
                + "<table>"
                + "<tr>"
                + "<td style='padding-bottom:10px;'>The day is finally here!</td>"
                + "</tr>"
                 + "<tr>"
                + "<td style='padding-bottom:10px;'>Your event is only hours away...We hope you and your guests have a great time! </td>"
                + "</tr>"
                 + "<tr>"
                + "<td >Order Details:</td>"
                + "</tr>"           
                 + "<tr>"
                + "<td>"+order.getCustomerName()+"</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"+order.getAddress()+"</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"+order.getCity()+", "+ order.getState()+ " " +order.getZipcode()+"</td>"
                + "</tr>"
                + "</table>"
                + "</td>"
                + "</tr>"
                
               + "<tr>"
                + "<td style='padding:0;'>"
                + "<table width='100%' style='border-spacing:0;broder-spacing: 0;'>"
                + "<tr>"
                + "<td class='three-columns' style='padding:0;text-align:center;font-size:0;padding-top:40px;padding-bottom:30px;'>"
                + "<table class='column' style='border-spacing:0;width:100%;max-width:200px;display:inline-block;vertical-align:top;'>"
                + "<tr>"
                + "<td class='padding' style='padding:15px;'>"
                + "<table class='content' style='border-spacing:0;font-size:15px;line-height:20px;'>"
                + "<tr>"
                + "<td style='padding:0;'>"
                + "<a href='#' style='text-decoration:none;color:blue;font-size:16px;'><img src='https://res.cloudinary.com/city-of-bounce/image/upload/v1578968329/Company/Niagara_Falls-NY/Absolute_Tent_Rentals/Products/BounceHouse-2.png' alt='product' width='150' style='border:0;max-width: 150px;' class='third-img'></a>"
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td style='padding:0;padding: 10px;'>"
                + "<p style='font-size: 17px; font-weight: bold;'>"
                + "O-G PRODUCT TITLE"
                + "</p>"
                + "<p>"
                + "SOmetkinga product intof atha you might wanns Show"
                + "</p>"
                + "<a href='#' style='text-decoration:none;color:blue;font-size:16px;'></a>"
                + "</td>"
                + "</tr>"
                + "</table>"
                + "</td>"
                + "</tr>"
                + "</table>"
                + "<table class='column' style='border-spacing:0;width:100%;max-width:200px;display:inline-block;vertical-align:top;'>"
                + "<tr>"
                + "<td class='padding' style='padding:0;padding:15px;'>"
                + "<table class='content' style='border-spacing:0;font-size:15px;line-height:20px;'>"
                + "<tr>"
                + "<td style='padding:0;'>"
                + "<a href='#' style='text-decoration:none;color:blue;font-size:16px;'><img src='https://res.cloudinary.com/city-of-bounce/image/upload/v1578968329/Company/Niagara_Falls-NY/Absolute_Tent_Rentals/Products/BounceHouse-2.png' alt='product' width='150' style='border:0;max-width: 150px;' class='third-img'></a>"
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td style='padding:0;padding: 10px;'>"
                + "<p style='font-size: 17px; font-weight: bold;'>"
                + "2ND PRODUCT TITLE"
                + "</p>"
                + "<p>"
                + "SOmetkinga product intof atha you might wanns"
                + "show"
                + "</p>"
                + "<a href='#' style='text-decoration:none;color:blue;font-size:16px;'></a>"
                + "</td>"
                + "</tr>"
                + "</table>"
                + "</td>"
                + "</tr>"
                + "</table>"
                + "<table class='column' style='border-spacing:0;width:100%;max-width:200px;display:inline-block;vertical-align:top;'>"
                + "<tr>"
                + "<td class='padding' style='padding:0;padding:15px;'>"
                + "<table class='content' style='border-spacing:0;font-size:15px;line-height:20px;'>"
                + "<tr>"
                + "<td style='padding:0;'>"
                + "<a href='#' style='text-decoration:none;color:blue;font-size:16px;'><img src='https://res.cloudinary.com/city-of-bounce/image/upload/v1578968329/Company/Niagara_Falls-NY/Absolute_Tent_Rentals/Products/BounceHouse-2.png' alt='product' width='150' style='border:0;max-width: 150px;' class='third-img-last'></a>"
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td style='padding:0;padding: 10px;'>"
                + "<p style='font-size: 17px; font-weight: bold;'>"
                + "THIRD PRODUCT TITLE"
                + "</p>"
                + "<p>"
                + "SOmetkinga product intof atha you might wanns"
                + "show"
                + "</p>"
                + "<a href='#' style='text-decoration:none;color:blue;font-size:16px;'></a>"
                + "</td>"
                + "</tr>"
                + "</table>"
                + "</td>"
                + "</tr>"
                + "</table>"
                + "</td>"
                + "</tr>"
                + "</table>"
                + "</td>"
                + "</tr>";

       
        msg.setContent(emailT + body + newFooter, "text/html");

        Transport.send(msg);
        System.out.println("Email sent!");
    }

}
