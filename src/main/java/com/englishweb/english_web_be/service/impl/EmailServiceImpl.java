package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordByEmail(String toEmail, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your Account Password");
        message.setText("Your account has been created.\n\nHere is your username: " + toEmail + " \nand password: " + password +
                "\n\nPlease change it after your first login.");
        mailSender.send(message);
    }

    public void sendOtpByEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your OTP Code");
        message.setText("We have received a request to reset your password.\n\nHere is your OTP code: " + otp +
                "\n\nPlease use this code within a limited time to reset your password. If you did not request this, please ignore this email.");
        mailSender.send(message);
    }
}
