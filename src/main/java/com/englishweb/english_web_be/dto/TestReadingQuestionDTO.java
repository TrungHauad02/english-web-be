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
public class TestReadingQuestionDTO implements BaseDTO {
    private String id;
    private String content;
    private int serial;
    private String explanation;
    private StatusEnum status;
    private List<TestReadingAnswerDTO> answers;
    private String testReadingId;

    @Override
    public String toString() {
        return "TestReadingQuestionDTO{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", serial=" + serial +
                ", explanation='" + explanation + '\'' +
                ", answers=" + answers +
                '}';
    }
}
