package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.model.interfacemodel.BaseEntity;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Random;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListeningAnswer implements BaseEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the listening answer'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'The content of the listening answer'")
    private String content;

    @Column(nullable = false, columnDefinition = "BIT(1) COMMENT 'Indicates if the answer is correct or not'")
    private boolean correct;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'The status of the listening answer, such as ACTIVE or INACTIVE'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "question_id", columnDefinition = "VARCHAR(255) COMMENT 'The associated listening question for this answer'")
    private ListeningQuestion question;

    @PrePersist
    private void generateId() {
        this.id = "Listen_Ans_" + System.nanoTime() + "_" + new Random().nextInt(10000);;
    }
}
