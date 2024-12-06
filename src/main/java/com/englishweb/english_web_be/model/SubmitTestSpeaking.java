package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.model.interfacemodel.BaseEntity;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmitTestSpeaking implements BaseEntity {

    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the submitted speaking test'")
    private String id;

    @Column(nullable = false, columnDefinition = "decimal(38,2) COMMENT 'Score for the submitted speaking test'")
    private BigDecimal score;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Content of the speaking test submission'")
    private String content;

    @Lob
    @Column(columnDefinition = "longtext COMMENT 'Transcript for the speaking test submission'")
    private String transcript;

    @Lob
    @Column(columnDefinition = "longtext COMMENT 'Comment for the speaking test submission'")
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'The status of the submitted speaking test'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "test_speaking_question_id", columnDefinition = "VARCHAR(255) COMMENT 'The associated speaking test question for this submission'")
    private TestSpeakingQuestion testSpeakingQuestion;

    @ManyToOne
    @JoinColumn(name = "submit_test_id", columnDefinition = "VARCHAR(255) COMMENT 'The associated submitted test for this speaking submission'")
    private SubmitTest submitTest;

    @PrePersist
    private void generateId() {
        this.id = "Submit_speaking_" + System.currentTimeMillis() + "_" + UUID.randomUUID().toString();
    }
}
