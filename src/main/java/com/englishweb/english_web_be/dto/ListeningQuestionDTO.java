package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;

import java.util.List;

public class ListeningQuestionDTO {
    String id;
    String content;
    int serial;
    String explanation;
    StatusEnum status;
    List<ListeningAnswerDTO> answers;

    public ListeningQuestionDTO() {
    }

    public ListeningQuestionDTO(String id, String content, int serial, String explanation, StatusEnum status, List<ListeningAnswerDTO> answers) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explanation = explanation;
        this.status = status;
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

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public List<ListeningAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<ListeningAnswerDTO> answers) {
        this.answers = answers;
    }
}
