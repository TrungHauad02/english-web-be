package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestMixingAnswerDTO implements BaseDTO {
    private String id;
    @NotBlank(message = "Nội dung không được để trống")
    private String content;
    @NotNull(message = "Trường isCorrect không được để trống")
    private Boolean isCorrect;
    @NotNull(message = "Trạng thái không được để trống")
    private StatusEnum status;
    @NotBlank(message = "Test Question Mixing ID không được để trống")
    private String testQuestionMixingId;
}
