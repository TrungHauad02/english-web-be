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
public class TestListeningResponseDTO implements BaseDTO {
    private String id;
    private int serial;
    private String content;
    private String transcript;
    private StatusEnum status;
    private List<TestListeningQuestionResponseDTO> questions;
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
