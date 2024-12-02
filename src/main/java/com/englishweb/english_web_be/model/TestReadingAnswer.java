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
public class TestReadingAnswer implements BaseEntity {

    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the test reading answer'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Content of the test reading answer'")
    private String content;

    @Column(nullable = false, columnDefinition = "BIT(1) COMMENT 'Indicates whether the answer is correct or not'")
    private Boolean isCorrect;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'Status of the test reading answer'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "test_reading_question_id", columnDefinition = "VARCHAR(255) COMMENT 'Foreign key to the related TestReadingQuestion'")
    private TestReadingQuestion testReadingQuestion;

    @PrePersist
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Generates unique ID before persisting the entity'")
    private void generateId() {
        this.id = "TestReadingAnswer_" + System.nanoTime();
    }

    public TestReadingAnswer(String id, String content, Boolean isCorrect, StatusEnum status) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
        this.status = status;
    }
}
