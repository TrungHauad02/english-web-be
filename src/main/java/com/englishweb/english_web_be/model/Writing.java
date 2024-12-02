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
public class Writing implements LessonEntity {

    @Id
    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the Writing'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Title of the Writing'")
    private String title;

    @Column(unique = true, nullable = false, columnDefinition = "INT COMMENT 'Serial number of the Writing'")
    private int serial;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Description of the Writing'")
    private String description;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Topic related to the Writing'")
    private String topic;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Image URL for the Writing'")
    private String image;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'Status of the Writing'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "Write_" + System.nanoTime();
    }
}
