package com.englishweb.english_web_be.model;

import jakarta.persistence.*;

@Entity
public class Vocabulary {
    @Id
    String id;
    String example;
    String image;
    String word;
    String phonetic;
    String meaning;
    WordTypeEnum wordType;
    StatusEnum status;
    @ManyToOne(targetEntity = Topic.class)
    String topic;
    public Vocabulary() {
    }

    public Vocabulary(String id, String example, String image, String word, String phonetic, String meaning, WordTypeEnum wordType, StatusEnum status, String topic) {
        this.id = id;
        this.example = example;
        this.image = image;
        this.word = word;
        this.phonetic = phonetic;
        this.meaning = meaning;
        this.wordType = wordType;
        this.status = status;
        this.topic = topic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public WordTypeEnum getWordType() {
        return wordType;
    }

    public void setWordType(WordTypeEnum wordType) {
        this.wordType = wordType;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
