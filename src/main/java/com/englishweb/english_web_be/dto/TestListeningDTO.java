package com.englishweb.english_web_be.dto;

import java.util.List;

public class TestListeningDTO {
    private String id;
    private int serial;
    private String content;
    private String transcript;
    private List<TestListeningQuestionDTO> questions;

    public TestListeningDTO(String id, int serial, String content, String transcript, List<TestListeningQuestionDTO> questions) {
        this.id = id;
        this.serial = serial;
        this.content = content;
        this.transcript = transcript;
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public List<TestListeningQuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<TestListeningQuestionDTO> questions) {
        this.questions = questions;
    }
}
