package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class TestReading {
    @Id
    private String id;
    private int serial;
    private String content;
    private String image;
    private StatusEnum statusEnum;
    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @OneToMany(mappedBy = "testReading", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestReadingQuestion> questions;


    public TestReading() {
    }

    public TestReading(String id, int serial, String content, String image, StatusEnum statusEnum) {
        this.id = id;
        this.serial = serial;
        this.content = content;
        this.image = image;
        this.statusEnum = statusEnum;
    }


    public void setImage(String image) {
        this.image = image;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<TestReadingQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<TestReadingQuestion> questions) {
        this.questions = questions;
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

    public String getImage() {
        return image;
    }

    public void setImaget(String image) {
        this.image = image;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}
