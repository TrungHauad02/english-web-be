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
public class TestListeningDTO implements BaseDTO {
    private String id;
    @Positive(message = "Serial không được để trống")
    private int serial;
    @NotBlank(message = "Nội dung không được để trống")
    private String content;
    @NotBlank(message = "Bản dịch không được để trống")
    private String transcript;

    private StatusEnum status;
    @NotBlank(message = "Test ID không được để trống")
    private String testId;

    private List<TestListeningQuestionDTO> questions;


    @Override
    public String toString() {
        return "TestListeningDTO{" +
                "id='" + id + '\'' +
                ", serial=" + serial +
                ", content='" + content + '\'' +
                ", transcript='" + transcript + '\'' +
                ", questions=" + questions +
                '}';
    }
}
