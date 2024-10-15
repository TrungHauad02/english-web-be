package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.*;

@Entity
public class Grammar {
    @Id
    private String id;
    @Column(nullable = false)
    private String title;
    @Column(unique = true, nullable = false)
    private int serial;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private String example;
    @Column(nullable = false)
    private String file;
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;

    @PrePersist
    private void generateId() {
        this.id = "Gram_" + System.currentTimeMillis();
    }

    @PrePersist
    private void generateId() {
        this.id = "Gram_" + System.currentTimeMillis();
    }

    public Grammar() {
    }

    public Grammar(String id, String title, int serial, String content, String image, String example, String file, StatusEnum status) {
        this.id = id;
        this.title = title;
        this.serial = serial;
        this.content = content;
        this.image = image;
        this.example = example;
        this.file = file;
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

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
