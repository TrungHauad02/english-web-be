package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestMixingTypeEnum;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestMixingQuestionDTO implements BaseDTO {
    private String id;
    private String content;
    private int serial;
    private String explanation;
    private TestMixingTypeEnum type;
    private StatusEnum status;
    private List<TestMixingAnswerDTO> answers;
    private String testId;
}