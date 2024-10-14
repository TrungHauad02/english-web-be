package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

@Entity
public class ListenPractice {
    @Id
    String id;
    String audioUrl;
    @Enumerated(EnumType.STRING)
    StatusEnum status;
    @OneToOne
    @JoinColumn(name = "listening_id")
    Listening listening;

    @PrePersist
    private void generateId() {
        this.id = "Listen_Practice_" + System.currentTimeMillis();
    }

    public ListenPractice() {
    }

    public ListenPractice(String id, String audioUrl, StatusEnum status, Listening listening) {
        this.id = id;
        this.audioUrl = audioUrl;
        this.status = status;
        this.listening = listening;
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

    public Listening getListening() {
        return listening;
    }

    public void setListening(Listening listening) {
        this.listening = listening;
    }
}
