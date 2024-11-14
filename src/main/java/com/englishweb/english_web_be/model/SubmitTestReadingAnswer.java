package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmitTestReadingAnswer implements BaseEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String submitTestId;

    @Column(nullable = false)
    private String questionId;

    @Column(nullable = false)
    private String answerId;

    @Column
    private String comment;

    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;


    @PrePersist
    private void generateId() {
        this.id = "Submit_read_ans_" + System.nanoTime();
    }
}
