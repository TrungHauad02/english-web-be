package com.englishweb.english_web_be.dto.response;


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
public class TestSpeakingResponseDTO implements BaseDTO {
    private String id;
    private String title;
    private int serial;
    private StatusEnum status;
    private List<TestSpeakingQuestionResponseDTO> questions;
    private String testId;

    @Override
    public String toString() {
        return "TestSpeakingDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", statusEnum=" + status +
                ", questions=" + questions +
                '}';
    }
}
