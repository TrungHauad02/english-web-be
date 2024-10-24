package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;

import java.util.List;
import java.util.Stack;

public class TestReadingDTO implements BaseDTO {
    private String id;
    private int serial;
    private String content;
    private String image;
    private StatusEnum status;
    private List<TestReadingQuestionDTO> questions;
    private String testId;

    public TestReadingDTO() {
    }

    public TestReadingDTO(String id, int serial, String content,String image,StatusEnum status, List<TestReadingQuestionDTO> questions) {
        this.id = id;
        this.serial = serial;
        this.content = content;
        this.questions = questions;
        this.status = status;
        this.image = image;
    }

    public TestReadingDTO(String id, int serial, String content, String image, StatusEnum status, List<TestReadingQuestionDTO> questions, String testId) {
        this.id = id;
        this.serial = serial;
        this.content = content;
        this.image = image;
        this.status = status;
        this.questions = questions;
        this.testId = testId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public List<TestReadingQuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<TestReadingQuestionDTO> questions) {
        this.questions = questions;
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
        return "TestReadingDTO{" +
                "id='" + id + '\'' +
                ", serial=" + serial +
                ", content='" + content + '\'' +
                ", questions=" + questions +
                '}';
    }
}
