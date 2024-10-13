package com.englishweb.english_web_be.dto;

import java.util.List;

public class TestListeningQuestionDTO {
    private String id;
    private String content;
    private int serial;
    private List<TestListeningAnswerDTO> answers;

    public TestListeningQuestionDTO(String id, String content, int serial, List<TestListeningAnswerDTO> answers) {
        this.id = id;
        this.content = content;
        this.serial = serial;
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

    public List<TestListeningAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<TestListeningAnswerDTO> answers) {
        this.answers = answers;
    }
}
