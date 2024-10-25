package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ListeningAnswerDTO implements BaseDTO {
    String id;
    @NotBlank(message = "Content cannot be empty")
    String content;
    @NotNull(message = "IsCorrect cannot be null")
    boolean correct;
    StatusEnum status;
    @NotBlank(message = "Listening question id cannot be empty")
    String questionId;

    public ListeningAnswerDTO() {
    }

    public ListeningAnswerDTO(String id, String content, boolean correct, StatusEnum status, String questionId) {
        this.id = id;
        this.content = content;
        this.correct = correct;
        this.status = status;
        this.questionId = questionId;
    }

    public ListeningAnswerDTO(String id, String content, boolean correct, StatusEnum status) {
        this.id = id;
        this.content = content;
        this.correct = correct;
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
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
