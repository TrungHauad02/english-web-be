package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;

import java.util.List;

public class TopicQuestionDTO implements BaseDTO {
    String id;
    String content;
    int serial;
    String explanation;
    StatusEnum status;
    List<TopicAnswerDTO> answers;

    public TopicQuestionDTO() {
    }

    public TopicQuestionDTO(String id, String content, int serial, String explanation, StatusEnum status, List<TopicAnswerDTO> answers) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explanation = explanation;
        this.status = status;
        this.answers = answers;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
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


    public List<TopicAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<TopicAnswerDTO> answers) {
        this.answers = answers;
    }
}
