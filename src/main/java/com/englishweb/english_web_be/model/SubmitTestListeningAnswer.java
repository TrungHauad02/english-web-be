package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.model.interfacemodel.BaseEntity;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SubmitTestListeningAnswer implements BaseEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String comment;

    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "submit_test_id")
    private SubmitTest submitTest;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private TestListeningQuestion question;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private TestListeningAnswer answer;

    @PrePersist
    private void generateId() {
        this.id = "Submit_listen_ans_" + System.currentTimeMillis() + "_" + UUID.randomUUID().toString();
    }
}
