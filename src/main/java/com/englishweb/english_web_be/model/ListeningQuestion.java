package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

@Entity
public class ListeningQuestion {
    @Id
    String id;
    String content;
    int serial;
    String explanation;
    @Enumerated(EnumType.STRING)
    StatusEnum status;
    @ManyToOne
    ListenPractice listenPractice;

    @PrePersist
    private void generateId() {
        this.id = "Listen_Que_" + System.currentTimeMillis();
    }

    public ListeningQuestion() {
    }

    public ListeningQuestion(String id, String content, int serial, String explanation, StatusEnum status, ListenPractice listenPractice) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explanation = explanation;
        this.status = status;
        this.listenPractice = listenPractice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public ListenPractice getListenPractice() {
        return listenPractice;
    }

    public void setListenPractice(ListenPractice listenPractice) {
        this.listenPractice = listenPractice;
    }
}
