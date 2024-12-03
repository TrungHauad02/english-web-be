package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.model.interfacemodel.BaseEntity;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmitTestReadingAnswer implements BaseEntity {

    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the submitted test reading answer'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Comment associated with the submitted reading answer'")
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'The status of the submitted reading answer'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "submit_test_id", columnDefinition = "VARCHAR(255) COMMENT 'The associated submitted test for this reading answer'")
    private SubmitTest submitTest;

    @ManyToOne
    @JoinColumn(name = "question_id", columnDefinition = "VARCHAR(255) COMMENT 'The associated reading question for this answer'")
    private TestReadingQuestion question;

    @ManyToOne
    @JoinColumn(name = "answer_id", columnDefinition = "VARCHAR(255) COMMENT 'The associated reading answer for this submission'")
    private TestReadingAnswer answer;

    @PrePersist
    private void generateId() {
        this.id = "Submit_read_ans_" + System.currentTimeMillis() + "_" + UUID.randomUUID().toString();
    }
}
