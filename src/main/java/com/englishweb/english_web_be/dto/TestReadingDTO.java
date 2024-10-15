package com.englishweb.english_web_be.dto;

import java.util.List;

public class TestReadingDTO {
    private String id;
    private int serial;
    private String content;
    private String image;
    private List<TestReadingQuestionDTO> questions;

    public TestReadingDTO() {
    }

    public TestReadingDTO(String id, int serial, String content,String image, List<TestReadingQuestionDTO> questions) {
        this.id = id;
        this.serial = serial;
        this.content = content;
        this.questions = questions;
        this.image = image;
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
