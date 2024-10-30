package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

@Entity
public class ListeningQuestion implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private int serial;
    @Column(nullable = false)
    private String explanation;
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;
    @ManyToOne
    private Listening listening;

    @PrePersist
    private void generateId() {
        this.id = "Listen_Que_" + System.nanoTime();
    }

    public ListeningQuestion() {
    }

    public ListeningQuestion(String id, String content, int serial, String explanation, StatusEnum status, Listening listening) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explanation = explanation;
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

    public Listening getListening() {
        return listening;
    }

    public void setListening(Listening listening) {
        this.listening = listening;
    }
}
