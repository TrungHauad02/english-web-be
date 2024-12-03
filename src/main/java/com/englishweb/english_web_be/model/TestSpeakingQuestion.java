package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.model.interfacemodel.BaseEntity;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestSpeakingQuestion implements BaseEntity {

    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the TestSpeakingQuestion'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Content of the TestSpeakingQuestion'")
    private String content;

    @Column(nullable = false, columnDefinition = "INT COMMENT 'Serial number for the TestSpeakingQuestion'")
    private int serial;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'Status of the TestSpeakingQuestion'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "TestSpeakingQuestion_" + System.nanoTime();
    }

    @ManyToOne
    @JoinColumn(name = "test_speaking_id", columnDefinition = "VARCHAR(255) COMMENT 'Foreign key to the related TestSpeaking'")
    private TestSpeaking testSpeaking;

    public TestSpeakingQuestion(String id, String content, int serial, StatusEnum status) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.status = status;
    }
}
