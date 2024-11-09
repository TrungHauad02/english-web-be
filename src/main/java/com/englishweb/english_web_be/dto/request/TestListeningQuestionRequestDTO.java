package com.englishweb.english_web_be.dto.request;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class TestListeningQuestionRequestDTO implements BaseDTO {
    private String id;
    private String content;
    private int serial;
    private List<TestListeningAnswerRequestDTO> answers;
    private StatusEnum status;
    private String testListeningId;

    @Override
    public String toString() {
        return "TestListeningQuestionDTO{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", serial=" + serial +
                ", answers=" + answers +
                '}';
    }
}
