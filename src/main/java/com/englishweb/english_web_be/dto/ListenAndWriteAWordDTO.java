package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class ListenAndWriteAWordDTO implements BaseDTO {
    String id;
    @Positive(message = "Serial number must be greater than 0")
    int serial;
    @NotBlank(message = "Audio url cannot be empty")
    String audioUrl;
    @NotBlank(message = "Sentence cannot be empty")
    String sentence;
    @PositiveOrZero(message = "Serial number must be equal or greater than 0")
    int missingIndex;
    @NotBlank(message = "Correct answer cannot be empty")
    String correctAnswer;
    StatusEnum status;
    @NotBlank(message = "Listening id cannot be empty")
    String listeningId;

    public ListenAndWriteAWordDTO() {
    }

    public ListenAndWriteAWordDTO(String id, int serial, String audioUrl, String sentence, int missingIndex, String correctAnswer, StatusEnum status, String listeningId) {
        this.id = id;
        this.serial = serial;
        this.audioUrl = audioUrl;
        this.sentence = sentence;
        this.missingIndex = missingIndex;
        this.correctAnswer = correctAnswer;
        this.status = status;
        this.listeningId = listeningId;
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

    public String getListeningId() {
        return listeningId;
    }

    public void setListeningId(String listeningId) {
        this.listeningId = listeningId;
    }
}
