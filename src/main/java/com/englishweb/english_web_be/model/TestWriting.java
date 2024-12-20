package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.model.interfacemodel.BaseEntity;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestWriting implements BaseEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for TestWriting'")
    private String id;

    @Column(nullable = false, columnDefinition = "INT COMMENT 'Serial number of the TestWriting'")
    private int serial;

    @Column(nullable = false, columnDefinition = "LONGTEXT COMMENT 'Content of the TestWriting'")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'Status of the TestWriting'")
    private StatusEnum status;

    public TestWriting(String testWritingId, int serial, String content, StatusEnum writingStatus) {
        this.id = testWritingId;
        this.serial = serial;
        this.content = content;
        this.status = writingStatus;
    }

    @PrePersist
    private void generateId() {
        this.id = "TestWriting_" + System.nanoTime();
    }

    @ManyToOne
    @JoinColumn(name = "test_id", columnDefinition = "VARCHAR(255) COMMENT 'Foreign key to the related Test'")
    private Test test;
}
