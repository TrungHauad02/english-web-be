package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;

public class ListeningAnswerDTO implements BaseDTO {
    String id;
    String content;
    boolean isCorrect;
    StatusEnum status;
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
