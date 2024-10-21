package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;




public class TestSpeakingQuestionDTO {

    private String id;
    private String content;
    private int serial;
    private StatusEnum status;
    private String testSpeakingId;



    public TestSpeakingQuestionDTO(String id, String content, int serial, StatusEnum status) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.status = status;
    }

    public TestSpeakingQuestionDTO(String id, String content, int serial, StatusEnum status, String testSpeakingId) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.status = status;
        this.testSpeakingId = testSpeakingId;
    }

    public TestSpeakingQuestionDTO() {

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

    public String getTestSpeakingId() {
        return testSpeakingId;
    }

    public void setTestSpeakingId(String testSpeakingId) {
        this.testSpeakingId = testSpeakingId;
    }

    @Override
    public String toString() {
        return "TestSpeakingQuestionDTO{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", serial=" + serial +
                ", status=" + status +
                '}';
    }
}
