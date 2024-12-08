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
public class Reading implements LessonEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the reading lesson'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'The title of the reading lesson'")
    private String title;

    @Column(unique = true, nullable = false, columnDefinition = "INT COMMENT 'The serial number of the reading lesson'")
    private int serial;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Description of the reading lesson'")
    private String description;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'File associated with the reading lesson'")
    private String file;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Image associated with the reading lesson'")
    private String image;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'The status of the reading lesson, such as ACTIVE or INACTIVE'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "Read_" + System.nanoTime();
    }
}
