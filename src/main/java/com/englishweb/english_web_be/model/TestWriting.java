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
public class TestWriting implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private int serial;
    @Column(nullable = false)
    private String content;
    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum;
    @PrePersist
    private void generateId() {
        this.id = "TestWriting_" + System.nanoTime();
    }

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;


    public TestWriting(String id, int serial, String content, StatusEnum statusEnum) {
        this.id = id;
        this.serial = serial;
        this.content = content;
        this.statusEnum = statusEnum;
    }
}
