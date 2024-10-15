package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

@Entity
public class TopicAnswer implements BaseEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private boolean isCorrect;
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;
    @ManyToOne
    @JoinColumn(name = "topic_question_id")
    private TopicQuestion question;

    @PrePersist
    private void generateId() {
        this.id = "Topic_ans_" + System.currentTimeMillis();
    }

    public TopicAnswer() {
    }

    public TopicAnswer(String id, String content, boolean isCorrect, StatusEnum status, TopicQuestion question) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
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
