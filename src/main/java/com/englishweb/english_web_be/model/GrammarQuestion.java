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
public class GrammarQuestion implements BaseEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the grammar question'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'The content of the grammar question'")
    private String content;

    @Column(nullable = false, columnDefinition = "INT COMMENT 'The serial number of the grammar question'")
    private int serial;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Explanation for the grammar question'")
    private String explanation;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'The status of the grammar question, such as ACTIVE or INACTIVE'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "grammar_id", columnDefinition = "VARCHAR(255) COMMENT 'The associated grammar for this question'")
    private Grammar grammar;

    @PrePersist
    private void generateId() {
        this.id = "Gram_que_" + System.nanoTime();
    }
}
