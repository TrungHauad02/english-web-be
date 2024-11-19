package com.englishweb.english_web_be.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentMixingQuestionRequest {
    @NotBlank(message = "Question content cannot be empty")
    private String questionContent;

    @NotEmpty(message = "Answers cannot be empty")
    private String[] answers;

    @NotBlank(message = "User answer cannot be empty")
    private String userAnswer;
}
