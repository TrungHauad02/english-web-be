package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TopicAnswerDTO implements BaseDTO {
    String id;
    @NotBlank(message = "Content cannot be empty")
    String content;
    @NotNull(message = "IsCorrect cannot be null")
    boolean isCorrect;
    StatusEnum status;
    @NotBlank(message = "Topic question id cannot be empty")
    String topicQuestionId;

    public TopicAnswerDTO() {
    }

    public TopicAnswerDTO(String id, String content, boolean isCorrect, StatusEnum status, String topicQuestionId) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
        this.status = status;
        this.topicQuestionId = topicQuestionId;
    }

    public TopicAnswerDTO(String id) {
        this.id = id;
    }

    public TopicAnswerDTO(String id, String content, boolean isCorrect, StatusEnum status) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getTopicQuestionId() {
        return topicQuestionId;
    }

    public void setTopicQuestionId(String topicQuestionId) {
        this.topicQuestionId = topicQuestionId;
    }
}
