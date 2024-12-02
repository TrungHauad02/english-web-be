package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordByEmail(String toEmail, String password) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("Email send by Admin H2T English <hungat418@gmail.com>");
            helper.setTo(toEmail);
            helper.setSubject("Your Account Password");

            String htmlContent = "<html><body>"
                    + "<h3>Your account has been created.</h3>"
                    + "<p>Here is your username: <strong>" + toEmail + "</strong></p>"
                    + "<p>Your password: <strong>" + password + "</strong></p>"
                    + "<p><em>Please change it after your first login.</em></p>"
                    + "</body></html>";

            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendOtpByEmail(String toEmail, String otp) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("Email send by Admin H2T English <hungat418@gmail.com>");
            helper.setTo(toEmail);
            helper.setSubject("Your OTP Code");

            String htmlContent = "<html><body>"
                    + "<h3>We have received a request to reset your password.</h3>"
                    + "<p>Here is your OTP code: <strong>" + otp + "</strong></p>"
                    + "<p><em>Please use this code within a limited time to reset your password. If you did not request this, please ignore this email.</em></p>"
                    + "</body></html>";

            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
