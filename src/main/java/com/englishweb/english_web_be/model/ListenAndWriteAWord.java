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
public class ListenAndWriteAWord implements BaseEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the listen and write word entry'")
    private String id;

    @Column(nullable = false, columnDefinition = "INT COMMENT 'The serial number of the listen and write word entry'")
    private int serial;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'The audio URL associated with the listen and write word entry'")
    private String audioUrl;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'The sentence related to the listen and write word entry'")
    private String sentence;

    @Column(nullable = false, columnDefinition = "INT COMMENT 'The index of the missing word in the sentence'")
    private int missingIndex = 0;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'The correct answer for the missing word'")
    private String correctAnswer;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'The status of the listen and write word entry, such as ACTIVE or INACTIVE'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @ManyToOne
    @JoinColumn(name="listening_id", columnDefinition = "VARCHAR(255) COMMENT 'The associated listening entry for this word'")
    private Listening listening;

    @PrePersist
    private void generateId() {
        this.id = "Listen_Word_" + System.nanoTime();
    }
}
