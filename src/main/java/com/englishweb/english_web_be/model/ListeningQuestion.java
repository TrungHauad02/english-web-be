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
public class ListeningQuestion implements BaseEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the listening question'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'The content of the listening question'")
    private String content;

    @Column(nullable = false, columnDefinition = "INT COMMENT 'The serial number of the listening question'")
    private int serial;

    @Column(nullable = false, columnDefinition = "LONGTEXT COMMENT 'Explanation for the listening question'")
    private String explanation;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'The status of the listening question, such as ACTIVE or INACTIVE'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "listening_id", columnDefinition = "VARCHAR(255) COMMENT 'The associated listening entry for this question'")
    private Listening listening;

    @PrePersist
    private void generateId() {
        this.id = "Listen_Que_" + System.nanoTime();
    }
}
