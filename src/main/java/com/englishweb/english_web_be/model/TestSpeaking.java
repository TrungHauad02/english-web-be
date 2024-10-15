package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class TestSpeaking {
    @Id
    private String id;
    private String title;
    private StatusEnum statusEnum;
    @OneToOne
    @JoinColumn(name = "test_id")
    private Test test;
    @OneToMany(mappedBy = "testSpeaking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestSpeakingQuestion> questions;


    public TestSpeaking() {
    }

    public TestSpeaking(String id, String title, StatusEnum statusEnum) {
        this.id = id;
        this.title = title;
        this.statusEnum = statusEnum;
    }

    public TestSpeaking(String id, String title, StatusEnum statusEnum, Test test, List<TestSpeakingQuestion> questions) {
        this.id = id;
        this.title = title;
        this.statusEnum = statusEnum;
        this.test = test;
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<TestSpeakingQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<TestSpeakingQuestion> questions) {
        this.questions = questions;
    }
}
