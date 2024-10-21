package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;

import java.util.List;

public class TestListeningDTO {
    private String id;
    private int serial;
    private String content;
    private String transcript;
    private StatusEnum status;
    private List<TestListeningQuestionDTO> questions;
    private String testId;

    public TestListeningDTO() {
    }

    public TestListeningDTO(String id, int serial, String content, String transcript, StatusEnum status, List<TestListeningQuestionDTO> questions) {
        this.id = id;
        this.serial = serial;
        this.content = content;
        this.transcript = transcript;
        this.questions = questions;
        this.status = status;
    }

    public TestListeningDTO(String id, int serial, String content, String transcript, StatusEnum status, List<TestListeningQuestionDTO> questions, String testId) {
        this.id = id;
        this.serial = serial;
        this.content = content;
        this.transcript = transcript;
        this.status = status;
        this.questions = questions;
        this.testId = testId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public List<TestListeningQuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<TestListeningQuestionDTO> questions) {
        this.questions = questions;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    @Override
    public String toString() {
        return "TestListeningDTO{" +
                "id='" + id + '\'' +
                ", serial=" + serial +
                ", content='" + content + '\'' +
                ", transcript='" + transcript + '\'' +
                ", questions=" + questions +
                '}';
    }
}
