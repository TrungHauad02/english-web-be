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
public class SubmitTestSpeaking implements BaseEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private BigDecimal score;

    @Column(nullable = false)
    private String content;

    @Lob
    private String explanation;

    @Lob
    private String comment;

    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "test_speaking_question_id")
    private TestSpeakingQuestion testSpeakingQuestion;

    @ManyToOne
    @JoinColumn(name = "submit_test_id")
    private SubmitTest submitTest;

    @PrePersist
    private void generateId() {
        this.id = "Submit_speaking_" + System.currentTimeMillis() + "_" + UUID.randomUUID().toString();
    }
}
