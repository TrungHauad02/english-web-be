package com.englishweb.english_web_be.dto.request;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestMixingTypeEnum;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestMixingQuestionRequestDTO implements BaseDTO {
    private String id;
    private String content;
    private int serial;
    private String explanation;
    private TestMixingTypeEnum type;
    private StatusEnum status;
    private List<TestMixingAnswerRequestDTO> answers;
    private String testId;
}