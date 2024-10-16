package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.WordTypeEnum;
import jakarta.validation.constraints.NotBlank;

public class VocabularyDTO implements BaseDTO {
    String id;
    @NotBlank(message = "Example cannot be empty")
    String example;
    @NotBlank(message = "Image cannot be empty")
    String image;
    @NotBlank(message = "Word cannot be empty")
    String word;
    @NotBlank(message = "Phonetic cannot be empty")
    String phonetic;
    @NotBlank(message = "Meaning cannot be empty")
    String meaning;
    @NotBlank(message = "Word type cannot be empty")
    WordTypeEnum wordType;
    StatusEnum status;
    @NotBlank(message = "Topic id cannot be empty")
    String topicId;

    public VocabularyDTO() {
    }

    public VocabularyDTO(String id, String example, String image, String word, String phonetic, String meaning, WordTypeEnum wordType, StatusEnum status, String topicId) {
        this.id = id;
        this.example = example;
        this.image = image;
        this.word = word;
        this.phonetic = phonetic;
        this.meaning = meaning;
        this.wordType = wordType;
        this.status = status;
        this.topicId = topicId;
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

    @Override
    public String getId() {
        return id;
    }

    @Override
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

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }
}
