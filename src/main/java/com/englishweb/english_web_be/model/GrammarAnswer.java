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
public class GrammarAnswer implements BaseEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the grammar answer'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'The content of the grammar answer'")
    private String content;

    @Column(nullable = false, columnDefinition = "bit(1) COMMENT 'Indicates if the answer is correct or not'")
    private boolean correct;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('ACTIVE','INACTIVE') COMMENT 'The status of the answer, such as ACTIVE or INACTIVE'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "question_id", columnDefinition = "VARCHAR(255) COMMENT 'The associated grammar question for this answer'")
    private GrammarQuestion question;

    @PrePersist
    private void generateId() {
        this.id = "Gram_ans_" + System.nanoTime();
    }
}
