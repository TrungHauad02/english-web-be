package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.model.SubmitTestListeningAnswer;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmitTestDTO implements BaseDTO {
    String id;
    @NotBlank(message = "Test ID cannot be empty")
    String testId;
    @NotBlank(message = "User ID cannot be empty")
    String userId;
    @NotNull(message = "Score must be a positive number")
    BigDecimal score;
    LocalDateTime submitTime;
    StatusEnum status;

    private List<SubmitTestListeningAnswerDTO> submitTestListeningAnswers;

    private List<SubmitTestReadingAnswerDTO> submitTestReadingAnswers;

    private List<SubmitTestWritingDTO> submitTestWritings;

    private List<SubmitTestSpeakingDTO> submitTestSpeakings;

    private List<SubmitTestMixingAnswerDTO> submitTestMixingAnswers;
}
