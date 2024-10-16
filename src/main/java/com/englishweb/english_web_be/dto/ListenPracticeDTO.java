package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;

public class ListenPracticeDTO implements BaseDTO {
    String id;
    @NotBlank(message = "Audio url cannot be empty")
    String audioUrl;
    StatusEnum status;
    @NotBlank(message = "Listening id url cannot be empty")
    String listeningId;

    public ListenPracticeDTO() {
    }

    public ListenPracticeDTO(String id, String audioUrl, StatusEnum status, String listeningId) {
        this.id = id;
        this.audioUrl = audioUrl;
        this.status = status;
        this.listeningId = listeningId;
    }

    public ListenPracticeDTO(String id, String audioUrl, StatusEnum status) {
        this.id = id;
        this.audioUrl = audioUrl;
        this.status = status;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
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

    public String getListeningId() {
        return listeningId;
    }

    public void setListeningId(String listeningId) {
        this.listeningId = listeningId;
    }
}
