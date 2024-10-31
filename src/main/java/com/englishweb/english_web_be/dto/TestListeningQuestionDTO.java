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
public class TestListeningQuestionDTO implements BaseDTO {
    private String id;
    private String content;
    private int serial;
    private List<TestListeningAnswerDTO> answers;
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
