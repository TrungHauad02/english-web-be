package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class TestVocabularyQuestion implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private int serial;
    @Column(nullable = false)
    private String explanation;
    @Enumerated(EnumType.STRING)
    private StatusEnum status=StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "TestVocabularyQuestion_" + System.nanoTime();
    }

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @OneToMany(mappedBy = "testVocabularyQuestion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestVocabularyAnswer> answers;


    public TestVocabularyQuestion() {
    }

    public TestVocabularyQuestion(String id, String content, int serial, String explanation, StatusEnum status) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explanation = explanation;
        this.status = status;
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

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explantion) {
        this.explanation = explantion;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public List<TestVocabularyAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<TestVocabularyAnswer> answers) {
        this.answers = answers;
    }
}
