package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class ReadingQuestionDTO implements BaseDTO{
    private String id;
    @NotBlank(message = "Content cannot be empty")
    private String content;
    @Positive(message = "Serial number must be greater than 0")
    private int serial;
    @NotBlank(message = "Explanation cannot be empty")
    private String explanation;
    private StatusEnum status;
    private List<ReadingAnswerDTO> answers;
    @NotBlank(message = "Reading id cannot be empty")
    private String readingId;

    public ReadingQuestionDTO() {
    }

    public ReadingQuestionDTO(String id, String content, int serial, String explanation, StatusEnum status, List<ReadingAnswerDTO> answers, String readingId) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explanation = explanation;
        this.status = status;
        this.answers = answers;
        this.readingId = readingId;
    }

    public ReadingQuestionDTO(String id, String content, int serial, String explanation, StatusEnum status, String readingId) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explanation = explanation;
        this.status = status;
        this.readingId = readingId;
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

    public String getReadingId() {
        return readingId;
    }

    public void setReadingId(String readingId) {
        this.readingId = readingId;
    }

    public List<ReadingAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<ReadingAnswerDTO> answers) {
        this.answers = answers;
    }
}
