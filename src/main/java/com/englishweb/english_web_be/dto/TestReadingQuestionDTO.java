package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;

import java.util.List;

public class TestReadingQuestionDTO implements BaseDTO {
    private String id;
    private String content;
    private int serial;
    private String explanation;
    private StatusEnum status;
    private List<TestReadingAnswerDTO> answers;
    private String testReadingId;

    public TestReadingQuestionDTO() {
    }

    public TestReadingQuestionDTO(String id, String content, int serial, String explanation,StatusEnum status, List<TestReadingAnswerDTO> answers) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explanation = explanation;
        this.status = status;
        this.answers = answers;
    }

    public TestReadingQuestionDTO(String id, String content, int serial, String explanation, StatusEnum status, List<TestReadingAnswerDTO> answers, String testReadingId) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explanation = explanation;
        this.status = status;
        this.answers = answers;
        this.testReadingId = testReadingId;
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

    public List<TestReadingAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<TestReadingAnswerDTO> answers) {
        this.answers = answers;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getTestReadingId() {
        return testReadingId;
    }

    public void setTestReadingId(String testReadingId) {
        this.testReadingId = testReadingId;
    }

    @Override
    public String toString() {
        return "TestReadingQuestionDTO{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", serial=" + serial +
                ", explanation='" + explanation + '\'' +
                ", answers=" + answers +
                '}';
    }
}
