package com.englishweb.english_web_be.model;


import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Test implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private int serial;
    @Column(nullable = false)
    private int duration;
    @Column(nullable = false)
    private TestTypeEnum type;
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "test_" + System.nanoTime();
    }

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestListening> testListenings;


    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestReading> testReadings;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestWriting> testWritings;


    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestMixingQuestion> testMixingQuestions;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestSpeaking> testSpeakings;



    public Test() {
    }


    public Test(String id, String title, int serial, int duration, TestTypeEnum type, StatusEnum status) {
        this.id = id;
        this.title = title;
        this.serial = serial;
        this.duration = duration;
        this.type = type;
        this.status = status;
    }

    public Test(String id, String title, int serial, int duration, TestTypeEnum type, StatusEnum status, List<TestListening> testListenings, List<TestReading> testReadings, List<TestWriting> testWritings, List<TestSpeaking> testSpeakings) {
        this.id = id;
        this.title = title;
        this.serial = serial;
        this.duration = duration;
        this.type = type;
        this.status = status;
        this.testListenings = testListenings;
        this.testReadings = testReadings;
        this.testWritings = testWritings;
        this.testSpeakings = testSpeakings;
    }

    public Test(String id, String title, int serial, int duration, TestTypeEnum type, StatusEnum status, List<TestListening> testListenings) {
        this.id = id;
        this.title = title;
        this.serial = serial;
        this.duration = duration;
        this.type = type;
        this.status = status;
        this.testListenings = testListenings;
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

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public TestTypeEnum getType() {
        return type;
    }

    public void setType(TestTypeEnum type) {
        this.type = type;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public List<TestListening> getTestListenings() {
        return testListenings;
    }

    public void setTestListenings(List<TestListening> testListenings) {
        this.testListenings = testListenings;
    }

    public List<TestReading> getTestReadings() {
        return testReadings;
    }

    public void setTestReadings(List<TestReading> testReadings) {
        this.testReadings = testReadings;
    }


    public List<TestWriting> getTestWritings() {
        return testWritings;
    }

    public void setTestWritings(List<TestWriting> testWritings) {
        this.testWritings = testWritings;
    }

    public List<TestMixingQuestion> getTestMixingQuestions() {
        return testMixingQuestions;
    }

    public void setTestMixingQuestions(List<TestMixingQuestion> testMixingQuestions) {
        this.testMixingQuestions = testMixingQuestions;
    }

    public List<TestSpeaking> getTestSpeakings() {
        return testSpeakings;
    }

    public void setTestSpeakings(List<TestSpeaking> testSpeakings) {
        this.testSpeakings = testSpeakings;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", serial=" + serial +
                ", duration=" + duration +
                ", type=" + type +
                ", status=" + status +
                ", testListenings=" + testListenings.toString() +
                ", testReadings=" + testReadings.toString()  +
                ", testWritings=" + testWritings.toString() +
                ", testSpeaking=" + testSpeakings.toString() +
                '}';
    }
}

