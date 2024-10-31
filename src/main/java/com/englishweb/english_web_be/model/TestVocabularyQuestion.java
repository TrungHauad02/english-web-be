package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestVocabularyQuestion implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private int serial;
    @Column(nullable = false)
    private String explanation;
    @Enumerated(EnumType.STRING)
    private StatusEnum status=StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "TestVocabularyQuestion_" + System.nanoTime();
    }

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @OneToMany(mappedBy = "testVocabularyQuestion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestVocabularyAnswer> answers;


    public TestVocabularyQuestion(String id, String content, int serial, String explanation, StatusEnum status) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explanation = explanation;
        this.status = status;
    }
}
