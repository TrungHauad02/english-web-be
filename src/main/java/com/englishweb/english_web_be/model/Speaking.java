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
public class Speaking implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String title;
    @Column(unique = true, nullable = false)
    private int serial;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private String topic;
    @Column(nullable = false)
    private int duration;
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "Speak_" + System.nanoTime();
    }
}
