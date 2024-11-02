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
public class TestReadingAnswerDTO implements BaseDTO {
    private String id;
    @NotBlank(message = "Nội dung không được để trống")
    private String content;
    @NotNull(message = "Trường isCorrect không được để trống")
    private Boolean isCorrect;
    @NotNull(message = "Trạng thái không được để trống")
    private StatusEnum status;
    @NotBlank(message = "Test Question Reading ID không được để trống")
    private String testQuestionReadingId;

    @Override
    public String toString() {
        return "TestReadingAnswerDTO{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
