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
public class ReadingAnswer implements BaseEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the reading answer'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'The content of the reading answer'")
    private String content;

    @Column(nullable = false, columnDefinition = "BIT(1) COMMENT 'Indicates if the answer is correct or not'")
    private boolean correct;

    @Column(nullable = false, columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'The status of the reading answer'")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "question_id", columnDefinition = "VARCHAR(255) COMMENT 'The associated reading question for this answer'")
    private ReadingQuestion question;

    @PrePersist
    private void generateId() {
        this.id = "Read_ans_" + System.nanoTime();
    }
}
