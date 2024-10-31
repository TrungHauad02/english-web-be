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
public class TestSpeakingDTO implements BaseDTO {
    private String id;
    private String title;
    private int serial;
    private StatusEnum status;
    private List<TestSpeakingQuestionDTO> questions;
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
