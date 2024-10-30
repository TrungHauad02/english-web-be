package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

@Entity
public class ListeningAnswer implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private boolean correct;
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;
    @ManyToOne
    @JoinColumn(name = "Listening_question_id")
    private ListeningQuestion question;

    @PrePersist
    private void generateId() {
        this.id = "Listen_Ans_" + System.nanoTime();
    }

    public ListeningAnswer() {
    }

    public ListeningAnswer(String id, String content, boolean correct, StatusEnum status, ListeningQuestion question) {
        this.id = id;
        this.content = content;
        this.correct = correct;
        this.status = status;
        this.question = question;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public ListeningQuestion getQuestion() {
        return question;
    }

    public void setQuestion(ListeningQuestion question) {
        this.question = question;
    }
}
