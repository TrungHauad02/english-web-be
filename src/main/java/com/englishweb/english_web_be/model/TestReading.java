package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.model.interfacemodel.BaseEntity;
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
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the test reading'")
    private String id;

    @Column(nullable = false, columnDefinition = "INT COMMENT 'Serial number of the test reading'")
    private int serial;

    @Column(nullable = false, columnDefinition = "LONGTEXT COMMENT 'Content of the test reading'")
    private String content;

    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Image associated with the test reading'")
    private String image;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'Status of the test reading'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "TestReading_" + System.nanoTime();
    }


    @ManyToOne
    @JoinColumn(name = "test_id", columnDefinition = "VARCHAR(255) COMMENT 'The associated test for this reading'")
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
