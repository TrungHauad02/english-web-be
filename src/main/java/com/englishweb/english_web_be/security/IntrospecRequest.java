package com.englishweb.english_web_be.security;

public class IntrospecRequest {
    String token;

    public IntrospecRequest() {}

    public IntrospecRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
