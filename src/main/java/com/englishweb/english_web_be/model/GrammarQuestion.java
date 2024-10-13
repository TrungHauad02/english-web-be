package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

@Entity
public class GrammarQuestion {
    @Id
    String id;
    String content;
    int serial;
    String explanation;
    @Enumerated(EnumType.STRING)
    StatusEnum status;
    @ManyToOne
    @JoinColumn(name = "grammar_id")
    Grammar grammar;

    @PrePersist
    private void generateId() {
        this.id = "Gram_que_" + System.currentTimeMillis();
    }

    public GrammarQuestion() {
    }

    public GrammarQuestion(String id, String content, int serial, String explanation, StatusEnum status, Grammar grammar) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explanation = explanation;
        this.status = status;
        this.grammar = grammar;
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

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Grammar getGrammar() {
        return grammar;
    }

    public void setGrammar(Grammar grammar) {
        this.grammar = grammar;
    }
}
