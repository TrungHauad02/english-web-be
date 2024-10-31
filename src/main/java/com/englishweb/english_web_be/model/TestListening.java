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
public class TestListening implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private int serial;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String transcript;
    @Enumerated(EnumType.STRING)
    private StatusEnum status =StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "TestListening_" + System.nanoTime();
    }

    @ManyToOne
    @JoinColumn(name = "test_id")
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
