package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.model.interfacemodel.BaseEntity;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class TestSpeaking implements BaseEntity {

    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the test speaking'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Title of the test speaking'")
    private String title;

    @Column(nullable = false, columnDefinition = "INT COMMENT 'Serial number of the test speaking'")
    private int serial;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'Status of the test speaking'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @PrePersist
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Generates unique ID before persisting the entity'")
    private void generateId() {
        this.id = "TestSpeaking_" + System.nanoTime();
    }

    @ManyToOne
    @JoinColumn(name = "test_id", columnDefinition = "VARCHAR(255) COMMENT 'Foreign key to the related Test'")
    private Test test;

    @OneToMany(mappedBy = "testSpeaking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestSpeakingQuestion> questions;

    public TestSpeaking() {
    }

    public TestSpeaking(String id, String title, int serial, StatusEnum status) {
        this.id = id;
        this.title = title;
        this.serial = serial;
        this.status = status;
    }

    public TestSpeaking(String id, String title, StatusEnum status, List<TestSpeakingQuestion> questions) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.questions = questions;
    }
}
