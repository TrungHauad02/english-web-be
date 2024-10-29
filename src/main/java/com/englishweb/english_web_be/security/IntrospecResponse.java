package com.englishweb.english_web_be.security;

public class IntrospecResponse {
    private boolean valid;

    public IntrospecResponse() {}

    public IntrospecResponse(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
