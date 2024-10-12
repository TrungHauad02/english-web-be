package com.englishweb.english_web_be.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TopicAnswer {
    @Id
    String id;
    String content;
    boolean isCorrect;
    StatusEnum status;
    @ManyToOne
    @JoinColumn(name = "topic_question_id")
    TopicQuestion question;

    public TopicAnswer() {
    }

    public TopicAnswer(String id, String content, boolean isCorrect, StatusEnum status, TopicQuestion question) {
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

    public TopicQuestion getQuestion() {
        return question;
    }

    public void setQuestion(TopicQuestion question) {
        this.question = question;
    }
}
