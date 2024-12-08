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
public class Speaking implements LessonEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the speaking lesson'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'The title of the speaking lesson'")
    private String title;

    @Column(unique = true, nullable = false, columnDefinition = "INT COMMENT 'The serial number of the speaking lesson'")
    private int serial;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Description of the speaking lesson'")
    private String description;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Link to the image in the speaking lesson'")
    private String image;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Topic of the speaking lesson'")
    private String topic;

    @Column(nullable = false, columnDefinition = "INT COMMENT 'Duration of the speaking lesson in seconds'")
    private int duration;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'The status of the speaking lesson'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "Speak_" + System.nanoTime();
    }
}
