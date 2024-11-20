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
    private BigDecimal score;

    @Column(nullable = false)
    private LocalDateTime submitTime;

    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    private void generateId() {
        this.id = "Submit_test_" + System.nanoTime();
    }
}
