package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;


public class TestWritingDTO {
    private String id;
    private int serial;
    private String content;
    private StatusEnum statusEnum;
    private String testId;

    public TestWritingDTO() {
    }

    public TestWritingDTO(String id, int serial, String content, StatusEnum statusEnum) {
        this.id = id;
        this.serial = serial;
        this.content = content;
        this.statusEnum = statusEnum;
    }

    public TestWritingDTO(String id, int serial, String content, StatusEnum statusEnum, String testId) {
        this.id = id;
        this.serial = serial;
        this.content = content;
        this.statusEnum = statusEnum;
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

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
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
                ", statusEnum=" + statusEnum +
                '}';
    }
}
