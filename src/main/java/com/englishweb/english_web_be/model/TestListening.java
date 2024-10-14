package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class TestListening {
    @Id
    private String id;
    private int serial;
    private String content;
    private String transcript;
    private StatusEnum statusEnum;
    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @OneToMany(mappedBy = "TestListening", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestListeningQuestion> questions;


    public TestListening() {
    }

    public TestListening(String id, int serial, String content, String transcript, StatusEnum statusEnum) {
        this.id = id;
        this.serial = serial;
        this.content = content;
        this.transcript = transcript;
        this.statusEnum = statusEnum;
    }

    public TestListening(String id, int serial, String content, String transcript, StatusEnum statusEnum, List<TestListeningQuestion> questions) {
        this.id = id;
        this.serial = serial;
        this.content = content;
        this.transcript = transcript;
        this.statusEnum = statusEnum;

        this.questions=questions;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<TestListeningQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<TestListeningQuestion> questions) {
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

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}
