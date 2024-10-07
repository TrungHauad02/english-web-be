package com.englishweb.english_web_be.dto;

public class TextToSpeechRequest {
    private String text;
    private String fileName;

    // Getters and setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
