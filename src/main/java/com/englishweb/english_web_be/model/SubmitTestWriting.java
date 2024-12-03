package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.model.interfacemodel.BaseEntity;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmitTestWriting implements BaseEntity {

    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the submitted writing test'")
    private String id;

    @Column(nullable = false, columnDefinition = "decimal(38,2) COMMENT 'Score for the submitted writing test'")
    private BigDecimal score;

    @Lob
    @Column(columnDefinition = "longtext COMMENT 'Content of the writing test submission'")
    private String content;

    @Lob
    @Column(columnDefinition = "longtext COMMENT 'Comment for the writing test submission'")
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'The status of the submitted writing test'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "test_writing_id", columnDefinition = "VARCHAR(255) COMMENT 'The associated writing test for this submission'")
    private TestWriting testWriting;

    @ManyToOne
    @JoinColumn(name = "submit_test_id", columnDefinition = "VARCHAR(255) COMMENT 'The associated submitted test for this writing submission'")
    private SubmitTest submitTest;

    @PrePersist
    private void generateId() {
        this.id = "Submit_writing_" + System.currentTimeMillis() + "_" + UUID.randomUUID().toString();
    }
}
