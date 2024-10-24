package com.englishweb.english_web_be.dto;


import com.englishweb.english_web_be.modelenum.StatusEnum;

import java.util.List;

public class TestGrammarQuestionDTO implements BaseDTO {
    private String id;
    private String content;
    private int serial;
    private String explanation;
    private StatusEnum status;
    private List<TestGrammarAnswerDTO> answers;
    private String testId;

    public TestGrammarQuestionDTO() {
    }

    public TestGrammarQuestionDTO(String id, String content, int serial, String explanation, StatusEnum status, List<TestGrammarAnswerDTO> answers) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explanation = explanation;
        this.status = status;
        this.answers = answers;
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

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
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

    public List<TestGrammarAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<TestGrammarAnswerDTO> answers) {
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
        return "TestGrammarQuestionDTO{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", serial=" + serial +
                ", explantion='" + explanation + '\'' +
                ", status=" + status +
                ", answers=" + answers +
                '}';
    }
}
