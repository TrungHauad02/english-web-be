package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrammarQuestionDTO implements BaseDTO {
    String id;
    @NotBlank(message = "Content cannot be empty")
    String content;
    @Positive(message = "Serial number must be greater than 0")
    int serial;
    @NotBlank(message = "Explanation cannot be empty")
    String explanation;
    StatusEnum status;
    List<GrammarAnswerDTO> answers;
    @NotBlank(message = "Grammar id cannot be empty")
    String grammarId;
}
