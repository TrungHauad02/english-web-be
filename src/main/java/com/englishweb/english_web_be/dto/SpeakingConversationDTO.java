package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;

public class SpeakingConversationDTO implements BaseDTO {
    String id;
    String name;
    int serial;
    String content;
    StatusEnum status;

    public SpeakingConversationDTO() {
    }

    public SpeakingConversationDTO(String id, String name, int serial, String content, StatusEnum status) {
        this.id = id;
        this.name = name;
        this.serial = serial;
        this.content = content;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
