package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ListeningAnswerDTO implements BaseDTO {
    String id;
    @NotBlank(message = "Content cannot be empty")
    String content;
    @NotNull(message = "IsCorrect cannot be null")
    boolean isCorrect;
    StatusEnum status;
    @NotBlank(message = "Listening question id cannot be empty")
    String listeningQuestionId;

    public ListeningAnswerDTO() {
    }

    public ListeningAnswerDTO(String id, String content, boolean isCorrect, StatusEnum status, String listeningQuestionId) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
        this.status = status;
        this.listeningQuestionId = listeningQuestionId;
    }

    public ListeningAnswerDTO(String id, String content, boolean isCorrect, StatusEnum status) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getListeningQuestionId() {
        return listeningQuestionId;
    }

    public void setListeningQuestionId(String listeningQuestionId) {
        this.listeningQuestionId = listeningQuestionId;
    }
}
