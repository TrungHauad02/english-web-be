package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

@Entity
public class ListenAndWriteAWord {
    @Id
    String id;
    int serial;
    String audioUrl;
    String sentence;
    int missingIndex;
    String correctAnswer;
    @Enumerated(EnumType.STRING)
    StatusEnum status;
    @ManyToOne
    @JoinColumn(name="listening_id")
    Listening listening;

    @PrePersist
    private void generateId() {
        this.id = "Listen_Word_" + System.currentTimeMillis();
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

    public String getId() {
        return id;
    }

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
