package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

@Entity
public class ListeningAnswer {
    @Id
    String id;
    String content;
    boolean isCorrect;
    @Enumerated(EnumType.STRING)
    StatusEnum status;
    @ManyToOne
    @JoinColumn(name = "Listening_question_id")
    ListeningQuestion question;

    @PrePersist
    private void generateId() {
        this.id = "Listen_Ans_" + System.currentTimeMillis();
    }

    public ListeningAnswer() {
    }

    public ListeningAnswer(String id, String content, boolean isCorrect, StatusEnum status, ListeningQuestion question) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
        this.status = status;
        this.question = question;
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

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
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
