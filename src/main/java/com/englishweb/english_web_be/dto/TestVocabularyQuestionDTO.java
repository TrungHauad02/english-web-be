package com.englishweb.english_web_be.dto;


import com.englishweb.english_web_be.modelenum.StatusEnum;

import java.util.List;

public class TestVocabularyQuestionDTO implements BaseDTO {
    private String id;
    private String content;
    private int serial;
    private String explantion;
    private StatusEnum status;
    private List<TestVocabularyAnswerDTO> answers;
    private String testId;

    public TestVocabularyQuestionDTO() {
    }

    public TestVocabularyQuestionDTO(String id, String content, int serial, String explantion, StatusEnum status, List<TestVocabularyAnswerDTO> answers) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explantion = explantion;
        this.status = status;
        this.answers = answers;
    }

    public TestVocabularyQuestionDTO(String id, String content, int serial, String explantion, List<TestVocabularyAnswerDTO> answers, StatusEnum status, String testId) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explantion = explantion;
        this.answers = answers;
        this.status = status;
        this.testId = testId;
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

    public String getExplantion() {
        return explantion;
    }

    public void setExplantion(String explantion) {
        this.explantion = explantion;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public List<TestVocabularyAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<TestVocabularyAnswerDTO> answers) {
        this.answers = answers;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    @Override
    public String toString() {
        return "TestVocabularyQuestionDTO{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", serial=" + serial +
                ", explantion='" + explantion + '\'' +
                ", status=" + status +
                ", answers=" + answers +
                '}';
    }
}
