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
public class TestReading implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private int serial;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String image;
    @Enumerated(EnumType.STRING)
    private StatusEnum status=StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "TestReading_" + System.nanoTime();
    }


    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @OneToMany(mappedBy = "testReading", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestReadingQuestion> questions;


    public TestReading(String id, int serial, String content, String image, StatusEnum statusEnum) {
        this.id = id;
        this.serial = serial;
        this.content = content;
        this.image = image;
        this.status = statusEnum;
    }

    public TestReading(String id, int serial, String content, String image, StatusEnum statusEnum, List<TestReadingQuestion> questions) {
        this.id = id;
        this.serial = serial;
        this.content = content;
        this.image = image;
        this.status = statusEnum;
        this.questions = questions;
    }
}
