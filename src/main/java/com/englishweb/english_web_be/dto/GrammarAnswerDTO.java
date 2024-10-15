package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;

public class GrammarAnswerDTO implements BaseDTO {
    String id;
    String content;
    boolean isCorrect;
    StatusEnum status;
    String questionId;

    public GrammarAnswerDTO() {
    }

    public GrammarAnswerDTO(String id, String content, boolean isCorrect, StatusEnum status, String questionId) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
        this.status = status;
        this.questionId = questionId;
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

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
