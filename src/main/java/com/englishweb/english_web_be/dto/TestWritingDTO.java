package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;


public class TestWritingDTO implements BaseDTO {
    private String id;
    private int serial;
    private String content;
    private StatusEnum status;
    private String testId;

    public TestWritingDTO() {
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

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    @Override
    public String toString() {
        return "TestWritingDTO{" +
                "id='" + id + '\'' +
                ", serial=" + serial +
                ", content='" + content + '\'' +
                ", statusEnum=" + status +
                '}';
    }
}
