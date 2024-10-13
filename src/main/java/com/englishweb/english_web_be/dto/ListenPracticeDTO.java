package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;

public class ListenPracticeDTO {
    String id;
    String audioUrl;
    StatusEnum status;

    public ListenPracticeDTO() {
    }

    public ListenPracticeDTO(String id, String audioUrl, StatusEnum status) {
        this.id = id;
        this.audioUrl = audioUrl;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
