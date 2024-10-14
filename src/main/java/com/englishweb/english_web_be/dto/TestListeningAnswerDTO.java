package com.englishweb.english_web_be.dto;

public class TestListeningAnswerDTO {
    private String id;
    private String content;
    private Boolean isCorrect;

    public TestListeningAnswerDTO(String id, String content, Boolean isCorrect) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
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

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean correct) {
        isCorrect = correct;
    }
}
