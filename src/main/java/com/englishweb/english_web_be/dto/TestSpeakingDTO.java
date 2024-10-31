package com.englishweb.english_web_be.dto;


import com.englishweb.english_web_be.modelenum.StatusEnum;


import java.util.List;

public class TestSpeakingDTO implements BaseDTO {
    private String id;
    private String title;
    private int serial;
    private StatusEnum status;
    private List<TestSpeakingQuestionDTO> questions;
    private String testId;

    public TestSpeakingDTO() {
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

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public List<TestSpeakingQuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<TestSpeakingQuestionDTO> questions) {
        this.questions = questions;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    @Override
    public String toString() {
        return "TestSpeakingDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", statusEnum=" + status +
                ", questions=" + questions +
                '}';
    }
}
