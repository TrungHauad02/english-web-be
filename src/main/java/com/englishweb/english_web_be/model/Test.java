package com.englishweb.english_web_be.model;


import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import jakarta.persistence.*;

import java.util.List;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
        this.id = "Test_" + System.nanoTime();
    }

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

    public Test(String id, String title, int serial, int duration, TestTypeEnum type, StatusEnum status, List<TestListening> testListenings, List<TestReading> testReadings, List<TestVocabularyQuestion> testVocabularyQuestions, List<TestGrammarQuestion> testGrammarQuestions, List<TestWriting> testWritings, List<TestSpeaking> testSpeakings) {
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
                ", testSpeaking=" + testSpeakings.toString() +
                '}';
    }
}

