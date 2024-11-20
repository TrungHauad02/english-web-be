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
    private String id;
    @Column(nullable = false)
    private String example;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private String word;
    @Column(nullable = false)
    private String phonetic;
    @Column(nullable = false)
    private String meaning;
    @Enumerated(EnumType.STRING)
    private WordTypeEnum wordType = WordTypeEnum.NOUN;
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @PrePersist
    private void generateId() {
        this.id = "Vocab_" + System.nanoTime();
    }
}
