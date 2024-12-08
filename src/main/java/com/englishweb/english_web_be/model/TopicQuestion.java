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
public class TopicQuestion implements BaseEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the Topic Question'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Content of the Topic Question'")
    private String content;

    @Column(nullable = false, columnDefinition = "INT COMMENT 'Serial number for ordering the questions'")
    private int serial;

    @Column(nullable = false, columnDefinition = "LONGTEXT COMMENT 'Explanation for the Topic Question'")
    private String explanation;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'Status of the Topic Question'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "topic_id", columnDefinition = "VARCHAR(255) COMMENT 'Foreign key linking the question to a specific Topic'")
    private Topic topic;

    @PrePersist
    private void generateId() {
        this.id = "Topic_que_" + System.nanoTime();
    }
}
