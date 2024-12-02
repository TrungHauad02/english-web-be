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
@Table(name = "grammar")
public class Grammar implements LessonEntity {
    @Id
    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'The id of the grammar lesson'")
    private String id;
    @Column(nullable = false, columnDefinition = "VARCHAR(255)  COMMENT 'The title of the grammar lesson'")
    private String title;
    @Column(unique = true, nullable = false, columnDefinition = "INT COMMENT 'The serial number of the grammar lesson'")
    private int serial;
    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'The content of the grammar lesson'")
    private String content;
    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'The description of the grammar lesson'")
    private String description;
    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Link to the image of the grammar lesson'")
    private String image;
    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'The example of the grammar lesson'")
    private String example;
    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Link to the file of the grammar lesson'")
    private String file;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum('ACTIVE','INACTIVE') COMMENT 'The status of the grammar lesson'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "Gram_" + System.nanoTime();
    }
}
