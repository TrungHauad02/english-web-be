package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class TestListeningQuestion implements BaseEntity {
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
        this.id = "TestListeningQuestion_" + System.nanoTime();
    }

    @ManyToOne
    @JoinColumn(name = "test_listening_id")
    private TestListening testListening;

    @OneToMany(mappedBy = "testListeningQuestion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestListeningAnswer> answersList;


    public TestListeningQuestion() {
    }

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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public TestListening getTestListening() {
        return testListening;
    }

    public void setTestListening(TestListening testListening) {
        this.testListening = testListening;
    }

    public List<TestListeningAnswer> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(List<TestListeningAnswer> answersList) {
        this.answersList = answersList;
    }
}
