package com.englishweb.english_web_be.model;


import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Test implements BaseEntity {
    @Id
    private String id;
    private String title;
    private int serial;
    private int duration;
    private TestTypeEnum type;
    private StatusEnum status;
    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestListening> testListenings;


    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestReading> testReadings;


    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestVocabularyQuestion> testVocabularyQuestions;


    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestGrammarQuestion> testGrammarQuestions;


    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestWriting> testWritings;

    @OneToOne(mappedBy = "test", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TestSpeaking testSpeaking;



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

    public Test(String id, String title, int serial, int duration, TestTypeEnum type, StatusEnum status, List<TestListening> testListenings, List<TestReading> testReadings, List<TestVocabularyQuestion> testVocabularyQuestions, List<TestGrammarQuestion> testGrammarQuestions, List<TestWriting> testWritings, TestSpeaking testSpeaking) {
        this.id = id;
        this.title = title;
        this.serial = serial;
        this.duration = duration;
        this.type = type;
        this.status = status;
        this.testListenings = testListenings;
        this.testReadings = testReadings;
        this.testVocabularyQuestions = testVocabularyQuestions;
        this.testGrammarQuestions = testGrammarQuestions;
        this.testWritings = testWritings;
        this.testSpeaking = testSpeaking;
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

    public List<TestVocabularyQuestion> getTestVocabularyQuestions() {
        return testVocabularyQuestions;
    }

    public void setTestVocabularyQuestions(List<TestVocabularyQuestion> testVocabularyQuestions) {
        this.testVocabularyQuestions = testVocabularyQuestions;
    }

    public List<TestGrammarQuestion> getTestGrammarQuestions() {
        return testGrammarQuestions;
    }

    public void setTestGrammarQuestions(List<TestGrammarQuestion> testGrammarQuestions) {
        this.testGrammarQuestions = testGrammarQuestions;
    }

    public List<TestWriting> getTestWritings() {
        return testWritings;
    }

    public void setTestWritings(List<TestWriting> testWritings) {
        this.testWritings = testWritings;
    }

    public TestSpeaking getTestSpeaking() {
        return testSpeaking;
    }

    public void setTestSpeaking(TestSpeaking testSpeaking) {
        this.testSpeaking = testSpeaking;
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
                ", testVocabularyQuestions=" + testVocabularyQuestions.toString() +
                ", testGrammarQuestions=" + testGrammarQuestions.toString() +
                ", testWritings=" + testWritings.toString() +
                ", testSpeaking=" + testSpeaking.toString() +
                '}';
    }
}

