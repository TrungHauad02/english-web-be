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
public class TestListeningQuestion implements BaseEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the listening question'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Content of the listening question'")
    private String content;

    @Column(nullable = false, columnDefinition = "INT COMMENT 'The serial number of the listening question'")
    private int serial;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'Status of the listening question'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "TestListeningQuestion_" + System.nanoTime();
    }

    @ManyToOne
    @JoinColumn(name = "test_listening_id", columnDefinition = "VARCHAR(255) COMMENT 'The associated listening entry for this question'")
    private TestListening testListening;

    @OneToMany(mappedBy = "testListeningQuestion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestListeningAnswer> answersList;


    public TestListeningQuestion(String id, int serial, String content, StatusEnum status) {
        this.id = id;
        this.serial = serial;
        this.content = content;
        this.status = status;
    }

    public TestListeningQuestion(String id, String content, int serial, StatusEnum status, List<TestListeningAnswer> answersList) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.status = status;
        this.answersList = answersList;
    }
}
