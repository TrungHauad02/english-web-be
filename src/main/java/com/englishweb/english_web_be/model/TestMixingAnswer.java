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
public class TestMixingAnswer implements BaseEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the mixing answer'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Content of the answer'")
    private String content;

    @Column(nullable = false, columnDefinition = "BIT(1) COMMENT 'Indicates if the answer is correct (1 for correct, 0 for incorrect)'")
    private Boolean isCorrect;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'Status of the mixing answer'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "TestMixingAnswer_" + System.nanoTime();
    }


    @ManyToOne
    @JoinColumn(name = "test_mixing_question_id", columnDefinition = "VARCHAR(255) COMMENT 'The associated mixing question for this answer'")
    private TestMixingQuestion testMixingQuestion;

    public TestMixingAnswer(String id, String content, Boolean isCorrect, StatusEnum status) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
        this.status = status;
    }
}
