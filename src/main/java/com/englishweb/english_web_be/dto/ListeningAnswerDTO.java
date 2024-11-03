package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListeningAnswerDTO implements BaseDTO {
    String id;
    @NotBlank(message = "Content cannot be empty")
    String content;
    @NotNull(message = "IsCorrect cannot be null")
    boolean correct;
    StatusEnum status;
    @NotBlank(message = "Listening question id cannot be empty")
    String questionId;
}
