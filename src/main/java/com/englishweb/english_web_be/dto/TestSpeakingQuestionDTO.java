package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestSpeakingQuestionDTO implements BaseDTO {
    private String id;
    private String content;
    private int serial;
    private StatusEnum status;
    private String testSpeakingId;

    @Override
    public String toString() {
        return "TestSpeakingQuestionDTO{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", serial=" + serial +
                ", status=" + status +
                '}';
    }
}
