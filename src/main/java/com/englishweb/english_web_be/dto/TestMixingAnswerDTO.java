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
public class TestMixingAnswerDTO implements BaseDTO {
    private String id;
    private String content;
    private Boolean isCorrect;
    private StatusEnum status;
    private String testQuestionMixingId;
}
