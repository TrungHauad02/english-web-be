package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.model.StatusEnum;
import com.englishweb.english_web_be.model.WordTypeEnum;

public class VocabularyDTO {
    String id;
    String example;
    String image;
    String word;
    String phonetic;
    String meaning;
    WordTypeEnum wordType;
    StatusEnum status;

    public VocabularyDTO() {
    }

    public VocabularyDTO(String id, String example, String image, String word, String phonetic, String meaning, WordTypeEnum wordType, StatusEnum status) {
        this.id = id;
        this.example = example;
        this.image = image;
        this.word = word;
        this.phonetic = phonetic;
        this.meaning = meaning;
        this.wordType = wordType;
        this.status = status;
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
}