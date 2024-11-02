package com.englishweb.english_web_be.dto.request;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadingQuestionRequestDTO implements BaseDTO {
    private String id;
    @NotBlank(message = "Content cannot be empty")
    private String content;
    @Positive(message = "Serial number must be greater than 0")
    private int serial;
    @NotBlank(message = "Explanation cannot be empty")
    private String explanation;
    private StatusEnum status;
    private List<ReadingAnswerRequestDTO> answers;
    @NotBlank(message = "Reading id cannot be empty")
    private String readingId;
}
