package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

@Entity
public class SpeakingConversation {
    @Id
    String id;
    String name;
    int serial;
    String content;
    @Enumerated(EnumType.STRING)
    StatusEnum status;
    @ManyToOne
    @JoinColumn(name = "speaking_id")
    Speaking speaking;

    @PrePersist
    private void generateId() {
        this.id = "Speak_con_" + System.currentTimeMillis();
    }

    public SpeakingConversation() {
    }

    public SpeakingConversation(String id, String name, int serial, String content, StatusEnum status, Speaking speaking) {
        this.id = id;
        this.name = name;
        this.serial = serial;
        this.content = content;
        this.status = status;
        this.speaking = speaking;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
