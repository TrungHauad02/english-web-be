package com.englishweb.english_web_be.dto.response;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestMixingAnswerResponseDTO implements BaseDTO {
    private String id;
    private String content;
    private Boolean isCorrect;
    private StatusEnum status;
    private String testQuestionMixingId;
}
