package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

@Entity
public class GrammarAnswer {
    @Id
    String id;
    String content;
    boolean isCorrect;
    @Enumerated(EnumType.STRING)
    StatusEnum status;
    @ManyToOne
    @JoinColumn(name = "grammar_question_id")
    GrammarQuestion question;

    @PrePersist
    private void generateId() {
        this.id = "Gram_ans_" + System.currentTimeMillis();
    }

    public GrammarAnswer() {
    }

    public GrammarAnswer(String id, String content, boolean isCorrect, StatusEnum status, GrammarQuestion question) {
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

    public GrammarQuestion getQuestion() {
        return question;
    }

    public void setQuestion(GrammarQuestion question) {
        this.question = question;
    }
}
