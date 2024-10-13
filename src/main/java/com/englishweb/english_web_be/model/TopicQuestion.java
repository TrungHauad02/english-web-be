package com.englishweb.english_web_be.model;

import jakarta.persistence.*;

@Entity
public class TopicQuestion {
    @Id
    String id;
    String content;
    int serial;
    String explanation;
    @Enumerated(EnumType.STRING)
    StatusEnum status;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    Topic topic;

    public TopicQuestion() {
    }

    public TopicQuestion(String id, String content, int serial, String explanation, StatusEnum status, Topic topic) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explanation = explanation;
        this.status = status;
        this.topic = topic;
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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
