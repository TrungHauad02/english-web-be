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
public class ReadingQuestion implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private int serial;
    @Column(nullable = false)
    private String explanation;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    @ManyToOne
    @JoinColumn(name = "reading_id")
    private Reading reading;

    @PrePersist
    private void generateId() {
        this.id = "Read_que_" + System.nanoTime();
    }
}
