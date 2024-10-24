package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;

public class TestListeningAnswerDTO implements BaseDTO {
    private String id;
    private String content;
    private Boolean isCorrect;
    private StatusEnum status;
    private String testQuestionListeningId;

    public TestListeningAnswerDTO() {
    }

    public TestListeningAnswerDTO(String id, String content, Boolean isCorrect) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
    }

    public TestListeningAnswerDTO(String id, String content, Boolean isCorrect, StatusEnum status, String testQuestionListeningId) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
        this.status = status;
        this.testQuestionListeningId = testQuestionListeningId;
    }

    public TestListeningAnswerDTO(String id, String content, Boolean isCorrect, StatusEnum status) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
        this.status = status;
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

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getTestQuestionListeningId() {
        return testQuestionListeningId;
    }

    public void setTestQuestionListeningId(String testQuestionListeningId) {
        this.testQuestionListeningId = testQuestionListeningId;
    }

    @Override
    public String toString() {
        return "TestListeningAnswerDTO{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
