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
public class Grammar implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String title;
    @Column(unique = true, nullable = false)
    private int serial;
    @Column(nullable = false)
    private String content;;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private String example;
    @Column(nullable = false)
    private String file;
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "Gram_" + System.nanoTime();
    }
}
