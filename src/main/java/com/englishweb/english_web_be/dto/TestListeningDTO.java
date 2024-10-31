package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestListeningDTO implements BaseDTO {
    private String id;
    private int serial;
    private String content;
    private String transcript;
    private StatusEnum status;
    private List<TestListeningQuestionDTO> questions;
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
