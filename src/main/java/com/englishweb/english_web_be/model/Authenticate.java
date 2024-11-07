package com.englishweb.english_web_be.model;

public class Authenticate {
    String token;
    boolean authenticate;
    String role;
    String id;

    public Authenticate() {}

    public Authenticate(boolean authenticate) {
        this.authenticate = authenticate;
    }

    public Authenticate(boolean authenticate, String token, String role, String id) {
        this.authenticate = authenticate;
        this.token = token;
        this.role = role;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

