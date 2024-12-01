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
    private String id;

    @Column(nullable = false)
    private BigDecimal score;

    @Lob
    private String content;

    @Lob
    private String comment;

    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "test_writing_id")
    private TestWriting testWriting;

    @ManyToOne
    @JoinColumn(name = "submit_test_id")
    private SubmitTest submitTest;

    @PrePersist
    private void generateId() {
        this.id = "Submit_writing_" + System.currentTimeMillis() + "_" + UUID.randomUUID().toString();
    }
}
