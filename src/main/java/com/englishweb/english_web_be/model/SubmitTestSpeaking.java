package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmitTestSpeaking implements BaseEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String testSpeakingQuestionId;

    @Column(nullable = false)
    private String submitTestId;

    @Column(precision = 5, scale = 2)
    private BigDecimal score;

    @Column
    private String content; // Assuming this stores the file path to the .mp3 file

    @Column
    private String explanation;

    @Column
    private String comment;

    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;


    @PrePersist
    private void generateId() {
        this.id = "Submit_speaking_" + System.nanoTime();
    }
}
