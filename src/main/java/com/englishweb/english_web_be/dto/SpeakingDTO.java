package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class SpeakingDTO implements BaseDTO {
    String id;
    @NotBlank(message = "Title cannot be empty")
    String title;
    @Positive(message = "Serial number must be greater than 0")
    int serial;
    @NotBlank(message = "Description cannot be empty")
    String description;
    @NotBlank(message = "Image cannot be empty")
    String image;
    @NotBlank(message = "Topic cannot be empty")
    String topic;
    @Positive(message = "Duration number must be greater than 0")
    int duration;
    StatusEnum status;

    public SpeakingDTO() {
    }

    public SpeakingDTO(String id, String title, int serial, String description, String image, String topic, int duration, StatusEnum status) {
        this.id = id;
        this.title = title;
        this.serial = serial;
        this.description = description;
        this.image = image;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public @NotBlank(message = "Topic cannot be empty") String getTopic() {
        return topic;
    }

    public void setTopic(@NotBlank(message = "Topic cannot be empty") String topic) {
        this.topic = topic;
    }

    @Positive(message = "Duration number must be greater than 0")
    public int getDuration() {
        return duration;
    }

    public void setDuration(@Positive(message = "Duration number must be greater than 0") int duration) {
        this.duration = duration;
    }
}
