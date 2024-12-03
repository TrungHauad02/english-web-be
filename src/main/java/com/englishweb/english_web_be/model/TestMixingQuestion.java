package com.englishweb.english_web_be.model;


import com.englishweb.english_web_be.model.interfacemodel.BaseEntity;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestMixingTypeEnum;
import jakarta.persistence.*;

import java.util.List;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestMixingQuestion implements BaseEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the mixing question'")
    private String id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT 'Content of the mixing question'")
    private String content;

    @Column(nullable = false, columnDefinition = "INT COMMENT 'Serial number of the mixing question'")
    private int serial;

    @Column(nullable = false, columnDefinition = "TEXT COMMENT 'Explanation for the mixing question'")
    private String explanation;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('GRAMMAR','VOCABULARY') COMMENT 'Type of the mixing question'")
    private TestMixingTypeEnum type;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'Status of the mixing question'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "TestMixingQuestion_" + System.nanoTime();
    }

    @ManyToOne
    @JoinColumn(name = "test_id", columnDefinition = "VARCHAR(255) COMMENT 'The associated test for this question'")
    private Test test;

    @OneToMany(mappedBy = "testMixingQuestion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestMixingAnswer> answers;

    public TestMixingQuestion(String id, String content, int serial, String explanation, TestMixingTypeEnum type, StatusEnum status) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explanation = explanation;
        this.type = type;
        this.status = status;
    }
}
