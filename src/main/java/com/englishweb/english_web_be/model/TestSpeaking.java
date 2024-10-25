package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class TestSpeaking implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String title;
    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum=StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "TestSpeaking_" + System.nanoTime();
    }


    @ManyToOne
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

    public TestSpeaking(String id, String title, StatusEnum statusEnum, List<TestSpeakingQuestion> questions) {
        this.id = id;
        this.title = title;
        this.statusEnum = statusEnum;
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
