package com.englishweb.english_web_be.model;


import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestMixingTypeEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class TestMixingQuestion implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private int serial;
    @Column(nullable = false)
    private String explanation;
    @Enumerated(EnumType.STRING)
    private TestMixingTypeEnum type;
    @Enumerated(EnumType.STRING)
    private StatusEnum status=StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "TestMixingQuestion_" + System.nanoTime();
    }

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @OneToMany(mappedBy = "testMixingQuestion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestMixingAnswer> answers;

    public TestMixingQuestion() {
    }

    public TestMixingQuestion(String id, String content, int serial, String explanation, TestMixingTypeEnum type, StatusEnum status) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explanation = explanation;
        this.type = type;
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

    public TestMixingTypeEnum getType() {
        return type;
    }

    public void setType(TestMixingTypeEnum type) {
        this.type = type;
    }

    public List<TestMixingAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<TestMixingAnswer> answers) {
        this.answers = answers;
    }
}