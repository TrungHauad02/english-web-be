package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.PrePersist;

public class ReadingDTO {
    String id;
    String title;
    int serial;
    String description;
    String content;
    String image;
    StatusEnum status;

    @PrePersist
    private void generateId() {
        this.id = "READ_" + System.currentTimeMillis();
    }

    public ReadingDTO() {
    }

    public ReadingDTO(String id, String title, int serial, String description, String content, String image, StatusEnum status) {
        this.id = id;
        this.title = title;
        this.serial = serial;
        this.description = description;
        this.content = content;
        this.image = image;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
