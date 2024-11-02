package com.englishweb.english_web_be.dto;


import com.englishweb.english_web_be.modelenum.StatusEnum;


import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestSpeakingDTO implements BaseDTO {
    private String id;
    @NotBlank(message = "Tiêu đề không được để trống")
    private String title;
    @Positive(message = "Serial phải là số dương")
    private int serial;
    @NotNull(message = "Trạng thái không được để trống")
    private StatusEnum status;
    @NotNull(message = "Danh sách câu hỏi không được để trống")
    private List<TestSpeakingQuestionDTO> questions;
    @NotBlank(message = "Test ID không được để trống")
    private String testId;

    @Override
    public String toString() {
        return "TestSpeakingDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", statusEnum=" + status +
                ", questions=" + questions +
                '}';
    }
}
