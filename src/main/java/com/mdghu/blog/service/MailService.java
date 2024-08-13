package com.mdghu.blog.service;


import com.mdghu.blog.entity.ConfirmationMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    // Corrected the annotation to use ${}
    @Value("${spring.mail.username}")
    private String from;

    public void sendConfirmationMail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        System.out.println(email);
        message.setFrom(from);
        message.setSubject("Confirmation of your account to get full user access");
        message.setText("This is your confirmation OTP "+otp);
        message.setTo(email);
        mailSender.send(message);
    }



}

