package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

@Entity
public class ReadingAnswer implements BaseEntity{
    @Id
    private String id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private boolean correct;
    @Column(nullable = false)
    private StatusEnum status;
    @ManyToOne
    @JoinColumn(name = "Reading_question_id")
    private ReadingQuestion question;

    @PrePersist
    private void generateId() {
        this.id = "Read_ans_" + System.currentTimeMillis();
    }

    public ReadingAnswer() {
    }

    public ReadingAnswer(String id, String content, boolean correct, StatusEnum status, ReadingQuestion question) {
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

    public ReadingQuestion getQuestion() {
        return question;
    }

    public void setQuestion(ReadingQuestion question) {
        this.question = question;
    }
}
