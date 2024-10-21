package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;

public class TestVocabularyAnswerDTO implements BaseDTO {
    private String id;
    private String content;
    private Boolean isCorrect;
    private StatusEnum status;
    private String testIdQuestionVocabulary;

    public TestVocabularyAnswerDTO() {
    }

    public TestVocabularyAnswerDTO(String id, String content, Boolean isCorrect, StatusEnum status) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
        this.status = status;
    }

    public TestVocabularyAnswerDTO(String id, String content, Boolean isCorrect, StatusEnum status, String testIdQuestionVocabulary) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
        this.status = status;
        this.testIdQuestionVocabulary = testIdQuestionVocabulary;
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

    public String getTestIdQuestionVocabulary() {
        return testIdQuestionVocabulary;
    }

    public void setTestIdQuestionVocabulary(String testIdQuestionVocabulary) {
        this.testIdQuestionVocabulary = testIdQuestionVocabulary;
    }

    @Override
    public String toString() {
        return "TestVocabularyAnswerDTO{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", isCorrect=" + isCorrect +
                ", status=" + status +
                '}';
    }
}
