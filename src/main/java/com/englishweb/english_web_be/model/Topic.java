package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

@Entity
public class Topic {
    @Id
    String id;
    String title;
    int serial;
    String image;
    String description;
    @Enumerated(EnumType.STRING)
    StatusEnum status;

    @PrePersist
    private void generateId() {
        this.id = "Topic_" + System.currentTimeMillis();
    }

    public Topic() {
    }

    public Topic(String id, String title, int serial, String image, String description, StatusEnum status) {
        this.id = id;
        this.title = title;
        this.serial = serial;
        this.image = image;
        this.description = description;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
