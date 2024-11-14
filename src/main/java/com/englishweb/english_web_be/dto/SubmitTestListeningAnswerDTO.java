package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.*;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmitTestListeningAnswerDTO implements BaseDTO {
    String id;
    @NotBlank(message = "Submit Test ID cannot be empty")
    String submitTestId;
    @NotBlank(message = "Question ID cannot be empty")
    String questionId;
    @NotBlank(message = "Answer ID cannot be empty")
    String answerId;
    @NotBlank(message = "Comment  cannot be empty")
    String comment;
    StatusEnum status;
}
