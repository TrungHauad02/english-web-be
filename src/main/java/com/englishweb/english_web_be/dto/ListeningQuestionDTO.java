package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class ListeningQuestionDTO implements BaseDTO {
    String id;
    @NotBlank(message = "Content cannot be empty")
    String content;
    @Positive(message = "Serial number must be greater than 0")
    int serial;
    @NotBlank(message = "Explanation cannot be empty")
    String explanation;
    StatusEnum status;
    List<ListeningAnswerDTO> answers;
    @NotBlank(message = "Listening id cannot be empty")
    String listeningId;

    public ListeningQuestionDTO() {
    }

    public ListeningQuestionDTO(String id, String content, int serial, String explanation, StatusEnum status, List<ListeningAnswerDTO> answers, String listeningId) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explanation = explanation;
        this.status = status;
        this.answers = answers;
        this.listeningId = listeningId;
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

    public List<ListeningAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<ListeningAnswerDTO> answers) {
        this.answers = answers;
    }

    public @NotBlank(message = "Listening id cannot be empty") String getListeningId() {
        return listeningId;
    }

    public void setListeningId(@NotBlank(message = "Listening id cannot be empty") String listeningId) {
        this.listeningId = listeningId;
    }
}
