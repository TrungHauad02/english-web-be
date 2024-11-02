package com.englishweb.english_web_be.dto.request;


import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestSpeakingRequestDTO implements BaseDTO {
    private String id;
    private String title;
    private int serial;
    private StatusEnum status;
    private List<TestSpeakingQuestionRequestDTO> questions;
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
