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
public class ReadingQuestion implements BaseEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the reading question'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'The content of the reading question'")
    private String content;

    @Column(nullable = false, columnDefinition = "INT COMMENT 'The serial number of the reading question'")
    private int serial;

    @Column(nullable = false, columnDefinition = "LONGTEXT COMMENT 'Explanation for the reading question'")
    private String explanation;

    @Column(nullable = false, columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'The status of the reading question'")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "reading_id", columnDefinition = "VARCHAR(255) COMMENT 'The associated reading lesson for this question'")
    private Reading reading;

    @PrePersist
    private void generateId() {
        this.id = "Read_que_" + System.nanoTime();
    }
}
