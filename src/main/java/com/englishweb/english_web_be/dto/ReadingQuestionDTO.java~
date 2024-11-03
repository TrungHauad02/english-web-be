package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadingQuestionDTO implements BaseDTO{
    private String id;
    @NotBlank(message = "Content cannot be empty")
    private String content;
    @Positive(message = "Serial number must be greater than 0")
    private int serial;
    @NotBlank(message = "Explanation cannot be empty")
    private String explanation;
    private StatusEnum status;
    private List<ReadingAnswerDTO> answers;
    @NotBlank(message = "Reading id cannot be empty")
    private String readingId;
}
