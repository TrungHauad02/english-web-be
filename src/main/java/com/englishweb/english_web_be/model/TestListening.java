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
public class TestListening implements BaseEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(255) COMMENT 'Unique identifier for the test listening'")
    private String id;

    @Column(nullable = false, columnDefinition = "INT COMMENT 'Serial number for the test listening'")
    private int serial;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT 'Content of the test listening'")
    private String content;

    @Column(nullable = false, columnDefinition = "LONGTEXT COMMENT 'Transcript of the test listening'")
    private String transcript;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE') COMMENT 'Status of the test listening'")
    private StatusEnum status = StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "TestListening_" + System.nanoTime();
    }

    @ManyToOne
    @JoinColumn(name = "test_id", columnDefinition = "VARCHAR(255) COMMENT 'The associated test for this listening'")
    private Test test;

    @OneToMany(mappedBy = "testListening", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestListeningQuestion> questions;


    public TestListening(String id, int serial, String content, String transcript, StatusEnum status) {
        this.id = id;
        this.serial = serial;
        this.content = content;
        this.transcript = transcript;
        this.status = status;
    }

    public TestListening(String id, int serial, String content, String transcript, StatusEnum status, List<TestListeningQuestion> questions) {
        this.id = id;
        this.serial = serial;
        this.content = content;
        this.transcript = transcript;
        this.status = status;
        this.questions=questions;
    }

    @Override
    public String toString() {
        return "TestListening{" +
                "id='" + id + '\'' +
                ", serial=" + serial +
                ", content='" + content + '\'' +
                ", transcript='" + transcript + '\'' +
                ", statusEnum=" + status;


    }
}
