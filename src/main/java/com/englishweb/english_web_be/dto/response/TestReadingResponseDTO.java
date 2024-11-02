package com.englishweb.english_web_be.dto.response;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestReadingResponseDTO implements BaseDTO {
    private String id;
    private int serial;
    private String content;
    private String image;
    private StatusEnum status;
    private List<TestReadingQuestionResponseDTO> questions;
    private String testId;

    @Override
    public String toString() {
        return "TestReadingDTO{" +
                "id='" + id + '\'' +
                ", serial=" + serial +
                ", content='" + content + '\'' +
                ", questions=" + questions +
                '}';
    }
}
