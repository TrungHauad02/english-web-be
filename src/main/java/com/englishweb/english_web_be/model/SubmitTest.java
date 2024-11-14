package com.englishweb.english_web_be.model;

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
    private String id;

    @Column(nullable = false)
    private String testId;

    @Column(nullable = false)
    private String userId;

    @Column(precision = 5, scale = 2)
    private BigDecimal score;

    @Column(nullable = false)
    private LocalDateTime submitTime;

    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;


    @PrePersist
    private void generateId() {
        this.id = "Submit_test_" + System.nanoTime();
    }
}
