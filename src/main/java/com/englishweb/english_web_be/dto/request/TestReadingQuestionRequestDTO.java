package com.englishweb.english_web_be.dto.request;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestReadingQuestionRequestDTO implements BaseDTO {
    private String id;
    private String content;
    private int serial;
    private String explanation;
    private StatusEnum status;
    private List<TestReadingAnswerRequestDTO> answers;
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