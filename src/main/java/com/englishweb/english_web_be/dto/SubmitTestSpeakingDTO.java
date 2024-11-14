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
    @Positive(message = "Score must be a positive number")
    BigDecimal score;
    @NotBlank(message = "Content cannot be empty")
    String content;
    @NotBlank(message = "Explanation cannot be empty")
    String explanation;
    @NotBlank(message = "Comment cannot be empty")
    String comment;
    StatusEnum status;
}
