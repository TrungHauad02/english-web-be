package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmitTestSpeakingDTO implements BaseDTO {
    String id;
    @NotBlank(message = "Test Speaking Question ID cannot be empty")
    String testSpeakingQuestionId;
    @NotBlank(message = "Submit Test ID cannot be empty")
    String submitTestId;

    BigDecimal score;

    String content;

    String explanation;

    String comment;
    StatusEnum status;
}
