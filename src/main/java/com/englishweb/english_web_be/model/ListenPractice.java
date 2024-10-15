package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

@Entity
public class ListenPractice implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String audioUrl;
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;
    @OneToOne
    @JoinColumn(name = "listening_id")
    private Listening listening;

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

    public Listening getListening() {
        return listening;
    }

    public void setListening(Listening listening) {
        this.listening = listening;
    }
}
