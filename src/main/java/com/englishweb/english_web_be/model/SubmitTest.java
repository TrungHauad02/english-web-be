package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.model.interfacemodel.BaseEntity;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmitTest implements BaseEntity {

    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the submitted test'")
    private String id;

    @Column(nullable = false, columnDefinition = "decimal(38,2) COMMENT 'The score obtained by the user in the test'")
    private BigDecimal score;

    @Column(nullable = false, columnDefinition = "datetime(6) COMMENT 'The time when the test was submitted'")
    private LocalDateTime submitTime;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'The status of the submitted test'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "test_id", columnDefinition = "VARCHAR(255) COMMENT 'The associated test for this submission'")
    private Test test;

    @ManyToOne
    @JoinColumn(name = "user_id", columnDefinition = "VARCHAR(255) COMMENT 'The user who submitted the test'")
    private User user;

    @PrePersist
    private void generateId() {
        this.id = "Submit_test_" + System.nanoTime();
    }
}
