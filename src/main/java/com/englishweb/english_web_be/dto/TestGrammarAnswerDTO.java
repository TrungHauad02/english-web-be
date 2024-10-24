package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;

public class TestGrammarAnswerDTO implements BaseDTO {
    private String id;
    private String content;
    private Boolean isCorrect;
    private StatusEnum status;
    private String testQuestionGrammarId;

    public TestGrammarAnswerDTO() {
    }

    public TestGrammarAnswerDTO(String id, String content, Boolean isCorrect, StatusEnum status, String testQuestionGrammarId) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
        this.status = status;
        this.testQuestionGrammarId = testQuestionGrammarId;
    }

    public TestGrammarAnswerDTO(String id, String content, Boolean isCorrect, StatusEnum status) {
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

    public String getTestQuestionGrammarId() {
        return testQuestionGrammarId;
    }

    public void setTestQuestionGrammarId(String testQuestionGrammarId) {
        this.testQuestionGrammarId = testQuestionGrammarId;
    }

    @Override
    public String toString() {
        return "TestGrammarAnswerDTO{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", isCorrect=" + isCorrect +
                ", status=" + status +
                '}';
    }
}
