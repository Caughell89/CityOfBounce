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
            + "<td height='6' style='padding:0;background-color:#1cacc8;background:linear-gradient(45deg, #1cacc8 0%, #16889c 100%);'></td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:0;"
            + "background-color:#ffffff;"
            + "padding: 4px;"
            + "padding-bottom: 2px;"
            + "text-align: center;'>"
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
            + "<a href='#' style='text-decoration:none;color:blue;font-size:16px;'><img src='https://res.cloudinary.com/city-of-bounce/image/upload/v1601314977/HomePage1_mftm4x.jpg' alt='Banner' width='600' style='border:0;max-width: 100%;'></a>"
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
            + "</tr>"
            + "<tr>"
            + "<td style='padding:0;'>"
            + "<table width='100%' style='border-spacing:0;border-spacing: 0;margin:0;'>"
            + "<tr>"
            + "<td style='background-color:#1cacc8;"
            + "background:linear-gradient(45deg, #1cacc8 0%, #16889c 100%);"
            + "padding: 12px;"
            + "padding-top:10px;"
            + "text-align: center;'>"
            + "<p style='"
            + "font-size: 14px;"
            + "font-weight: bold;"
            + "color: #ffffff;"
            + "margin-bottom: 13px;"
            + "'>"
            + "Connect with us"
            + "</p>"
            + "<a href='https://www.facebook.com/cityofbounce' style='text-decoration:none;;font-size:16px;margin:8px;'><img src='https://res.cloudinary.com/city-of-bounce/image/upload/v1600184987/facebook-f-brands_du4fj1.png' alt='facebook link'  style='border:0;'></a>"
            + "<a href='https://twitter.com/CityOfBounce' style='text-decoration:none;font-size:16px;margin:8px;'><img src='https://res.cloudinary.com/city-of-bounce/image/upload/v1600184952/twitter-brands_md5xe6.png' alt='twitter link'  style='border:0;'></a>"
            + "<a href='https://www.instagram.com/cityofbounce' style='text-decoration:none;font-size:16px;margin:8px;'><img src='https://res.cloudinary.com/city-of-bounce/image/upload/v1600184974/instagram-brands_dbklix.png' alt='instagram link' style='border:0;'></a>"
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
            + "font-size: 12px;"
            + "margin-top: 6px;"
            + "margin-bottom: 6px;'>"
            + "&#xA9 " + year + " City of Bounce, Inc. All rights reserved"
            + "</p>"
            + "<p style='font-size: 12px; margin-bottom: 6px;'>"
            + "Address if you wanna list one"
            + "</p>"
            + "<p style='font-size: 13px; margin-bottom: 6px;'>"
            + "<a href='mailto:cityofbounce@gmail.com' style='text-decoration:none;color:#1cacc8;font-size:16px;'>cityofbounce@gmail.com</a>"
            + "</p>"
            + "<p style='font-size: 13px; margin-bottom: 6px;'>"
            + "<a href='tel:17162003543' style='text-decoration:none;color:#1cacc8;font-size:16px;'>716-200-3543</a>"
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

//        msg.setContent("<html lang='en'><head><meta http-equiv='Content-Type content='text/html; charset=UTF-8'>"
//                + "<meta name='viewport' content='width=device-width, initial-scale=1'>"
//                + "<meta http-equiv='X-UA-Compatible' content='IE=edge'><title></title><style type='text/css'></style></head>"
//                + "<body style='margin:0; padding:20px; background-color:#ffffff; color:#6d6d6d; max-width:600px;'>"
//                + "<center><table width=''100% border='0' cellpadding='0' cellspacing='0' bgcolor='#ffffff'>"
//                + "<tr><td style='border-bottom: 1px solid #cccccc'><img style='width:20%; height:auto; margin-bottom: 10px;' src='https://res.cloudinary.com/city-of-bounce/image/upload/v1558473737/LogoTealText.png' alt='City Of Bounce Logo'/></td></tr>"
//                + "<tr><td style='padding-top:12px;'><h2>Let's get to work!</h2></td></tr>"
//                + "<tr><td style='padding-bottom:12px;'>  " + company.getCompanyName() + " wants you to join CityOfBounce!</td></tr>"
//                + "<tr><td >In order to get started, click the link to register.</td></tr>"
//                + "</table>"
//                + "<br>"
//                + "<table style='text-align:center;' align=center>"
//                + "<tr><td><h2>" + company.getCompanyName() + "</h2></td></tr>"
//                + "<tr><td><img style='height:100px;width:auto;border-radius:50%;' src='" + company.getCompanyLogo() + " 'alt='Company Logo'/></td></tr>"
//                + "<tr><td style='font-weight:bold; padding-top:12px'>" + company.getLocation() + ", " + company.getStateAbbr() + "</td></tr>"
//                + "<tr><td text-align:center;'><a style='text-decoration:none; color:white;' href='http://localhost:3000/CompanyRegistration/" + email + "/" + company.getCompanyUrl() + "'><div style='margin: 12px;padding: 12px;color:white; font-weight:bold; background-color:#1cacc8; font-size:14px; text-align:center; cursor:pointer; border-radius:1px;'>REGISTER<div></a></td></tr>"
//                + "<tr><td><a href=\"http://\"\n"
//                + "style=\"background: -moz-linear-gradient(right, #01b7ab 0%, #13558c 100%);\n"
//                + "background: -webkit-gradient(left top, left bottom, color-stop(0%, #01b7ab), color-stop(100%, #13558c));\n"
//                + "background: -webkit-linear-gradient(top, #01b7ab 0%, #13558c 100%);\n"
//                + "background: -o-linear-gradient(top, #01b7ab 0%, #13558c 100%);\n"
//                + "background: -ms-linear-gradient(top, #01b7ab 0%, #13558c 100%);\n"
//                + "background: linear-gradient(to bottom, #01b7ab 0%, #13558c 100%);\n"
//                + "filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#01b7ab', endColorstr='#13558c', GradientType=0 );\n"
//                + "color:#ffffff;display:inline-block;font-family:sans-serif;font-size:13px;text-transform:uppercase;letter-spacing:.02em;line-height:40px;text-align:center;text-decoration:none;width:400px;-webkit-text-size-adjust:none;\">TURN UP TURN UP</a></td></tr>"
//                + "</table>"
//                + "<br>"
//                + "<table style='padding-bottom:24px' align=left>"
//                + "<tr><td style='padding-bottom:12px;'>Thanks,</td></tr>"
//                + "<tr><td>The City Of Bounce Team</td></tr>"
//                + "</table><br>" + footer + "</center></body></html>", "text/html");
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

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("cityofbounce@gmail.com", "City Of Bounce"));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        msg.setSubject("Thanks for your order");

        msg.setContent("<html lang='en'><head><meta http-equiv='Content-Type content='text/html; charset=UTF-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1'>"
                + "<meta http-equiv='X-UA-Compatible' content='IE=edge'><title></title><style type='text/css'></style></head>"
                + "<body style='margin:0; padding:20px; background-color:#ffffff; color:#6d6d6d;'>"
                + "<center><table width=''100% border='0' cellpadding='0' cellspacing='0' bgcolor='#ffffff'>"
                + "<tr><td style='border-bottom: 1px solid #cccccc'><img style='width:20%; height:auto; margin-bottom: 10px;' src='https://res.cloudinary.com/city-of-bounce/image/upload/v1558473737/LogoTealText.png' alt='City Of Bounce Logo'/></td></tr>"
                + "<tr><td style='padding-top:12px;'><h2>Let's get ready to party!</h2></td></tr>"
                + "<tr><td style='padding-bottom:12px;'>Details of your order are listed below:</td></tr>"
                + "</table>"
                + "<br>"
                + "<table style='text-align:center;' align=center>"
                + "<tr><td><h2></h2></td></tr>"
                + "<tr><td style='font-weight:bold; padding-top:12px'></td></tr>"
                + "<tr><td text-align:center;'><a style='text-decoration:none; color:white;' href='http://localhost:3000/OrderConfirmation/'" + order.getOrderId() + "><div style='margin: 12px;padding: 12px;color:white; font-weight:bold; background-color:#1cacc8; font-size:14px; text-align:center; cursor:pointer; border-radius:1px;'>REGISTER<div></a></td></tr>"
                + "</table>"
                + "<br>"
                + "<table style='padding-bottom:24px' align=left>"
                + "<tr><td style='padding-bottom:12px;'>Thanks,</td></tr>"
                + "<tr><td>The City Of Bounce Team</td></tr>"
                + "</table></center></body></html>", "text/html");

        Transport.send(msg);
        System.out.println("Email sent!");
    }

}
