package com.englishweb.english_web_be.model;

public class Authenticate {
    String token;
    boolean authenticate;

    public Authenticate() {}

    public Authenticate(boolean authenticate) {
        this.authenticate = authenticate;
    }

    public Authenticate(boolean authenticate, String token) {
        this.authenticate = authenticate;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(boolean authenticate) {
        this.authenticate = authenticate;
    }
}

