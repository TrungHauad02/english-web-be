package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

import java.util.List;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestSpeaking implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String title;
    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum=StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "TestSpeaking_" + System.nanoTime();
    }


    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;
    @OneToMany(mappedBy = "testSpeaking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestSpeakingQuestion> questions;


    public TestSpeaking(String id, String title, StatusEnum statusEnum) {
        this.id = id;
        this.title = title;
        this.statusEnum = statusEnum;
    }

    public TestSpeaking(String id, String title, StatusEnum statusEnum, List<TestSpeakingQuestion> questions) {
        this.id = id;
        this.title = title;
        this.statusEnum = statusEnum;
        this.questions = questions;
    }
}
