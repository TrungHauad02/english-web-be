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
public class ReadingAnswer implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private boolean correct;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    @ManyToOne
    @JoinColumn(name = "Reading_question_id")
    private ReadingQuestion question;

    @PrePersist
    private void generateId() {
        this.id = "Read_ans_" + System.nanoTime();
    }
}
