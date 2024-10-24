package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;

import java.util.List;

public class TestListeningQuestionDTO implements BaseDTO {
    private String id;
    private String content;
    private int serial;
    private List<TestListeningAnswerDTO> answers;
    private StatusEnum status;
    private String testListeningId;

    public TestListeningQuestionDTO() {
    }

    public TestListeningQuestionDTO(String id, String content, int serial,StatusEnum status, List<TestListeningAnswerDTO> answers) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.answers = answers;
        this.status = status;
    }

    public TestListeningQuestionDTO(String id, String content, int serial, List<TestListeningAnswerDTO> answers, StatusEnum status, String testListeningId) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.answers = answers;
        this.status = status;
        this.testListeningId = testListeningId;
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

    public List<TestListeningAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<TestListeningAnswerDTO> answers) {
        this.answers = answers;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getTestListeningId() {
        return testListeningId;
    }

    public void setTestListeningId(String testListeningId) {
        this.testListeningId = testListeningId;
    }

    @Override
    public String toString() {
        return "TestListeningQuestionDTO{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", serial=" + serial +
                ", answers=" + answers +
                '}';
    }
}
