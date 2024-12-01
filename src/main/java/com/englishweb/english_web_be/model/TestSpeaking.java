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
    private String id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private int serial;
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "TestSpeaking_" + System.nanoTime();
    }


    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;
    @OneToMany(mappedBy = "testSpeaking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestSpeakingQuestion> questions;


    public TestSpeaking() {
    }

    public TestSpeaking(String id, String title, int serial, StatusEnum statusEnum) {
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
