package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;

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
public class TestReadingDTO implements BaseDTO {
    private String id;
    @Positive(message = "Serial phải là số dương")
    private int serial;
    @NotBlank(message = "Nội dung không được để trống")
    private String content;
    @NotBlank(message = "Hình ảnh không được để trống")
    private String image;
    @NotNull(message = "Trạng thái không được để trống")
    private StatusEnum status;
    @NotNull(message = "Danh sách câu hỏi không được để trống")
    private List<TestReadingQuestionDTO> questions;
    @NotBlank(message = "Test ID không được để trống")
    private String testId;


    @Override
    public String toString() {
        return "TestReadingDTO{" +
                "id='" + id + '\'' +
                ", serial=" + serial +
                ", content='" + content + '\'' +
                ", questions=" + questions +
                '}';
    }
}
