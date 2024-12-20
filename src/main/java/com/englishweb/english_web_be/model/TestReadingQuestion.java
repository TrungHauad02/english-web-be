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
public class TestReadingQuestion implements BaseEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the test reading question'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Content of the test reading question'")
    private String content;

    @Column(nullable = false, columnDefinition = "INT COMMENT 'Serial number of the test reading question'")
    private int serial;

    @Column(nullable = false, columnDefinition = "LONGTEXT COMMENT 'Explanation of the test reading question'")
    private String explanation;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'Status of the test reading question'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "TestReadingQuestion_" + System.nanoTime();
    }

    @ManyToOne
    @JoinColumn(name = "test_reading_id", columnDefinition = "VARCHAR(255) COMMENT 'Foreign key to the related TestReading'")
    private TestReading testReading;

    @OneToMany(mappedBy = "testReadingQuestion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestReadingAnswer> answers;

    public TestReadingQuestion(String id, String content, int serial, String explanation, StatusEnum status) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explanation = explanation;
        this.status = status;
    }

    public TestReadingQuestion(String id, String content, int serial, String explanation, StatusEnum status, List<TestReadingAnswer> answers) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explanation = explanation;
        this.status = status;
        this.answers = answers;
    }
}
