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
public class ListeningQuestion implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String content;
    @Column(unique = true, nullable = false)
    private int serial;
    @Column(nullable = false)
    private String explanation;
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;
    @ManyToOne
    private Listening listening;

    @PrePersist
    private void generateId() {
        this.id = "Listen_Que_" + System.nanoTime();
    }
}
