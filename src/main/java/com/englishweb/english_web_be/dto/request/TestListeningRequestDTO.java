package com.englishweb.english_web_be.dto.request;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder

public class TestListeningRequestDTO implements BaseDTO {
    private String id;
    private int serial;
    private String content;
    private String transcript;
    private StatusEnum status;
    private List<TestListeningQuestionRequestDTO> questions;
    private String testId;

    @Override
    public String toString() {
        return "TestListeningDTO{" +
                "id='" + id + '\'' +
                ", serial=" + serial +
                ", content='" + content + '\'' +
                ", transcript='" + transcript + '\'' +
                ", questions=" + questions +
                '}';
    }
}
