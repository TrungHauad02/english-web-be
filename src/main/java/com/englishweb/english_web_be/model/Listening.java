package com.englishweb.english_web_be.model;

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
public class Listening implements LessonEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the listening entry'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'The title of the listening entry'")
    private String title;

    @Column(unique = true, nullable = false, columnDefinition = "INT COMMENT 'The serial number of the listening entry'")
    private int serial;

    @Column(nullable = false, columnDefinition = "LONGTEXT COMMENT 'Description of the listening entry'")
    private String description;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Image associated with the listening entry'")
    private String image;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Audio URL associated with the listening entry'")
    private String audioUrl;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'The status of the listening entry, such as ACTIVE or INACTIVE'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "Listen_" + System.nanoTime();
    }
}
