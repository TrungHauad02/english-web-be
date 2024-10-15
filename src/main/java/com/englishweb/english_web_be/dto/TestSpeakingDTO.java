package com.englishweb.english_web_be.dto;


import com.englishweb.english_web_be.modelenum.StatusEnum;


import java.util.List;

public class TestSpeakingDTO {
    private String id;
    private String title;
    private StatusEnum statusEnum;
    private List<TestSpeakingQuestionDTO> questions;

    public TestSpeakingDTO() {
    }

    public TestSpeakingDTO(String id, String title, StatusEnum statusEnum, List<TestSpeakingQuestionDTO> questions) {
        this.id = id;
        this.title = title;
        this.statusEnum = statusEnum;
        this.questions = questions;
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

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public List<TestSpeakingQuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<TestSpeakingQuestionDTO> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "TestSpeakingDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", statusEnum=" + statusEnum +
                ", questions=" + questions +
                '}';
    }
}
