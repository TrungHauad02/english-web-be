package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.model.interfacemodel.BaseEntity;
import com.englishweb.english_web_be.model.interfacemodel.LessonEntity;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topic")
public class Topic implements LessonEntity {

    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the Topic'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Title of the Topic'")
    private String title;

    @Column(unique = true, nullable = false, columnDefinition = "INT COMMENT 'Serial number of the Topic'")
    private int serial;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Image URL or path associated with the Topic'")
    private String image;

    @Column(nullable = false, columnDefinition = "LONGTEXT COMMENT 'Description of the Topic'")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'Status of the Topic'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "Topic_" + System.nanoTime();
    }
}
