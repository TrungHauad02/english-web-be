package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.model.interfacemodel.BaseEntity;
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
public class TestReadingQuestion implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private int serial;
    @Column(nullable = false)
    private String explantion;
    @Enumerated(EnumType.STRING)
    private StatusEnum status=StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "TestReadingQuestion_" + System.nanoTime();
    }


    @ManyToOne
    @JoinColumn(name = "test_reading_id")
    private TestReading testReading;

    @OneToMany(mappedBy = "testReadingQuestion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestReadingAnswer> answers;

    public TestReadingQuestion(String id, String content, int serial, String explantion, StatusEnum status) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explantion = explantion;
        this.status = status;
    }

    public TestReadingQuestion(String id, String content, int serial, String explantion, StatusEnum status, List<TestReadingAnswer> answers) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explantion = explantion;
        this.status = status;
        this.answers = answers;
    }
}
