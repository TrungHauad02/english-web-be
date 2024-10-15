package com.englishweb.english_web_be.dto;

import java.util.List;

public class TestReadingQuestionDTO {
    private String id;
    private String content;
    private int serial;
    private String explanation;
    private List<TestReadingAnswerDTO> answers;

    public TestReadingQuestionDTO() {
    }

    public TestReadingQuestionDTO(String id, String content, int serial, String explanation, List<TestReadingAnswerDTO> answers) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explanation = explanation;
        this.answers = answers;
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

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public List<TestReadingAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<TestReadingAnswerDTO> answers) {
        this.answers = answers;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    @Override
    public String toString() {
        return "TestReadingQuestionDTO{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", serial=" + serial +
                ", explanation='" + explanation + '\'' +
                ", answers=" + answers +
                '}';
    }
}
