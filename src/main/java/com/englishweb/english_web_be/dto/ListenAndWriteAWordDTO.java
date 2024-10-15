package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;

public class ListenAndWriteAWordDTO implements BaseDTO {
    String id;
    int serial;
    String audioUrl;
    String sentence;
    int missingIndex;
    String correctAnswer;
    StatusEnum status;

    public ListenAndWriteAWordDTO() {
    }

    public ListenAndWriteAWordDTO(String id, int serial, String audioUrl, String sentence, int missingIndex, String correctAnswer, StatusEnum status) {
        this.id = id;
        this.serial = serial;
        this.audioUrl = audioUrl;
        this.sentence = sentence;
        this.missingIndex = missingIndex;
        this.correctAnswer = correctAnswer;
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
}
