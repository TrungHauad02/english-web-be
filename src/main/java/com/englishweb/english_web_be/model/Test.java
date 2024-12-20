package com.englishweb.english_web_be.model;


import com.englishweb.english_web_be.model.interfacemodel.BaseEntity;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Test implements BaseEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the test'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Title of the test'")
    private String title;

    @Column(nullable = false, columnDefinition = "INT COMMENT 'Serial number of the test'")
    private int serial;

    @Column(nullable = false, columnDefinition = "INT COMMENT 'Duration in minutes for the test'")
    private int duration;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('LISTENING','MIXING','READING','SPEAKING','WRITING') COMMENT 'Type of the test'")
    private TestTypeEnum type;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'Status of the test'")
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
}

