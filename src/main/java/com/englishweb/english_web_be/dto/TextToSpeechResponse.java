package com.englishweb.english_web_be.dto;

public class TextToSpeechResponse {
    private String audioUrl;

    public TextToSpeechResponse(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }
}
