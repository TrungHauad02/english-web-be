package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class TestWriting implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private int serial;
    @Column(nullable = false)
    private String content;
    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum;
    @PrePersist
    private void generateId() {
        this.id = "TestWriting_" + System.nanoTime();
    }

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;


    public TestWriting() {
    }

    public TestWriting(String id, int serial, String content, StatusEnum statusEnum) {
        this.id = id;
        this.serial = serial;
        this.content = content;
        this.statusEnum = statusEnum;
    }

    public TestWriting(String id, int serial, String content, StatusEnum statusEnum, Test test) {
        this.id = id;
        this.serial = serial;
        this.content = content;
        this.statusEnum = statusEnum;
        this.test = test;
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

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
