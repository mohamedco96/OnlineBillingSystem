package com.billingsystem.messaging;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package messaging;
//
//import java.util.Properties;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
///**
// *
// * @author M. Ibrahim
// */
//public class email {
//
//    public static void sendMail(String recepient) throws Exception {
//        System.out.println("Properties to send email");
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        props.put("mail.smtp.auth", "true");
//
//        String email = "mohamedco2014@gmail.com";
//        String pass = "mohmed2010";
//
//        Session session = Session.getInstance(props, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(email, pass);
//            }
//        });
//
//        Message message = preporeMessage(session, email, recepient);
//
//        Transport.send(message);
//        System.out.println("Message send successfully ");
//    }
//
//    private static Message preporeMessage(Session session, String email, String recepient) {
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(email));
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
//            message.setSubject("Billing System");
//            String text="<h1>Billing info</h1><br/>";
//            message.setContent(text,"text/html");
//            return message;
//        } catch (Exception ex) {
//            Logger.getLogger(email.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    public static void main(String[] args) throws Exception {
//        sendMail("mohamedco215@gmail.com");
//    }
//
//}
