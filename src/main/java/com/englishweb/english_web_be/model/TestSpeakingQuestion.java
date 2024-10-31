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
public class TestSpeakingQuestion implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private int serial;
    @Enumerated(EnumType.STRING)
    private StatusEnum status=StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "TestSpeakingQuestion_" + System.nanoTime();
    }

    @ManyToOne
    @JoinColumn(name = "test_speaking_id")
    private TestSpeaking testSpeaking;

    public TestSpeakingQuestion(String id, String content, int serial, StatusEnum status) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.status = status;
    }
}
