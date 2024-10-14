package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

@Entity
public class SpeakingTopic {
    @Id
    private String id;
    @Column(nullable = false)
    private String topic;
    @Column(nullable = false)
    private int duration;
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;
    @OneToOne
    private Speaking speaking;

    @PrePersist
    private void generateId() {
        this.id = "Speak_topic_" + System.currentTimeMillis();
    }

    public SpeakingTopic() {
    }

    public SpeakingTopic(String id, String topic, int duration, StatusEnum status, Speaking speaking) {
        this.id = id;
        this.topic = topic;
        this.duration = duration;
        this.status = status;
        this.speaking = speaking;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Speaking getSpeaking() {
        return speaking;
    }

    public void setSpeaking(Speaking speaking) {
        this.speaking = speaking;
    }
}
