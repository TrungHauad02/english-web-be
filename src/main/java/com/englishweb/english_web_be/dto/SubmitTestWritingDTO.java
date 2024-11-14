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
public class SubmitTestWritingDTO implements BaseDTO {
    String id;
    @NotBlank(message = "Test Writing ID cannot be empty")
    String testWritingId;
    @NotBlank(message = "Submit Test ID cannot be empty")
    String submitTestId;
    @Positive(message = "Score must be a positive number")
    BigDecimal score;
    @NotBlank(message = "Content  cannot be empty")
    String content;
    @NotBlank(message = "Comment cannot be empty")
    String comment;
    StatusEnum status;
}
