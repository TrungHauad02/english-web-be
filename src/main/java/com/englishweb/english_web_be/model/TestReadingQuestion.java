package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class TestReadingQuestion {
    @Id
    private String id;
    private String content;
    private int serial;
    private String explantion;
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "test_reading_id")
    private TestReading testReading;

    @OneToMany(mappedBy = "testReadingQuestion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestReadingAnswer> answers;

    public TestReadingQuestion() {
    }

    public TestReadingQuestion(String id, String content, int serial, String explantion, StatusEnum status) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explantion = explantion;
        this.status = status;
    }

    public TestReadingQuestion(String id, String content, int serial, String explantion, StatusEnum status, List<TestReadingAnswer> answers) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explantion = explantion;
        this.status = status;
        this.answers = answers;
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

    public String getExplantion() {
        return explantion;
    }

    public void setExplantion(String explantion) {
        this.explantion = explantion;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public TestReading getTestReading() {
        return testReading;
    }

    public void setTestReading(TestReading testReading) {
        this.testReading = testReading;
    }

    public List<TestReadingAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<TestReadingAnswer> answers) {
        this.answers = answers;
    }
}
