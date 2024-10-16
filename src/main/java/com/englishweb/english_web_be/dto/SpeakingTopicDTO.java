package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class SpeakingTopicDTO implements BaseDTO {
    String id;
    @NotBlank(message = "Topic cannot be empty")
    String topic;
    @PositiveOrZero(message = "Duration number must be equal or greater than 0")
    int duration;
    StatusEnum status;

    public SpeakingTopicDTO() {
    }

    public SpeakingTopicDTO(String id, String topic, int duration, StatusEnum status) {
        this.id = id;
        this.topic = topic;
        this.duration = duration;
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
