package com.englishweb.english_web_be.service;

public interface EmailService {
    void sendPasswordByEmail(String toEmail, String password);
    void sendOtpByEmail(String toEmail, String otp);
}
