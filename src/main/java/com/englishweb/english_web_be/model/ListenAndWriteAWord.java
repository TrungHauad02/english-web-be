package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

@Entity
public class ListenAndWriteAWord implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private int serial;
    @Column(nullable = false)
    private String audioUrl;
    @Column(nullable = false)
    private String sentence;
    @Column(nullable = false)
    private int missingIndex = 0;
    @Column(nullable = false)
    private String correctAnswer;
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;
    @ManyToOne
    @JoinColumn(name="listening_id")
    private Listening listening;

    @PrePersist
    private void generateId() {
        this.id = "Listen_Word_" + System.nanoTime();
    }

    public ListenAndWriteAWord() {
    }

    public ListenAndWriteAWord(String id, int serial, String audioUrl, String sentence, int missingIndex, String correctAnswer, StatusEnum status, Listening listening) {
        this.id = id;
        this.serial = serial;
        this.audioUrl = audioUrl;
        this.sentence = sentence;
        this.missingIndex = missingIndex;
        this.correctAnswer = correctAnswer;
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

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public int getMissingIndex() {
        return missingIndex;
    }

    public void setMissingIndex(int missingIndex) {
        this.missingIndex = missingIndex;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
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
