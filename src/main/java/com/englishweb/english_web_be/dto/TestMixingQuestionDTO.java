package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestMixingTypeEnum;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestMixingQuestionDTO implements BaseDTO {
    private String id;
    @NotBlank(message = "Nội dung không được để trống")
    private String content;
    @Positive(message = "Serial phải là số dương")
    private int serial;
    @NotBlank(message = "Giải thích không được để trống")
    private String explanation;
    @NotNull(message = "Loại câu hỏi không được để trống")
    private TestMixingTypeEnum type;
    @NotBlank(message = "Trạng thái không được để trống")
    private StatusEnum status;
    @NotNull(message = "Danh sách câu trả lời không được để trống")
    private List<TestMixingAnswerDTO> answers;
    @NotBlank(message = "Test ID không được để trống")
    private String testId;
}