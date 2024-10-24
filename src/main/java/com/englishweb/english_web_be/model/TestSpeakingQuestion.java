package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;


import java.util.List;

@Entity
public class TestSpeakingQuestion implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private int serial;
    @Enumerated(EnumType.STRING)
    private StatusEnum status=StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "TestSpeakingQuestion_" + System.nanoTime();
    }

    @ManyToOne
    @JoinColumn(name = "test_speaking_id")
    private TestSpeaking testSpeaking;
    public TestSpeakingQuestion() {

    }

    public TestSpeakingQuestion(String id, String content, int serial, StatusEnum status) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.status = status;
    }

    public TestSpeakingQuestion(String id, String content, int serial, StatusEnum status, TestSpeaking testSpeaking) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.status = status;
        this.testSpeaking = testSpeaking;
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

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public TestSpeaking getTestSpeaking() {
        return testSpeaking;
    }

    public void setTestSpeaking(TestSpeaking testSpeaking) {
        this.testSpeaking = testSpeaking;
    }
}
