package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GrammarAnswerDTO implements BaseDTO {
    String id;
    @NotBlank(message = "Content cannot be empty")
    String content;
    @NotNull(message = "Correct cannot be null")
    boolean correct;
    StatusEnum status;
    @NotNull(message = "Question id cannot be null")
    String questionId;
}
