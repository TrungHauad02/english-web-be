package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.model.interfacemodel.BaseEntity;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.WordTypeEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vocabulary implements BaseEntity {

    @Id
    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the Vocabulary'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Example sentence or context for the word'")
    private String example;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Image representing the word'")
    private String image;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'The word itself'")
    private String word;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Phonetic transcription of the word'")
    private String phonetic;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Meaning or definition of the word'")
    private String meaning;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('ADJECTIVE', 'ADVERB', 'NOUN', 'VERB') COMMENT 'Type of the word (e.g., Noun, Verb, etc.)'")
    private WordTypeEnum wordType = WordTypeEnum.NOUN;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'Status of the vocabulary word'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "topic_id", columnDefinition = "VARCHAR(255) COMMENT 'Foreign key linking to the Topic entity'")
    private Topic topic;

    @PrePersist
    private void generateId() {
        this.id = "Vocab_" + System.nanoTime();
    }
}
