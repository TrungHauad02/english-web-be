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
public class TestListeningQuestionDTO implements BaseDTO {
    private String id;
    @NotBlank(message = "Nội dung không được để trống")
    private String content;
    @Positive(message = "Serial phải là số dương")
    private int serial;
    @NotNull(message = "Danh sách câu trả lời không được để trống")
    private List<TestListeningAnswerDTO> answers;

    private StatusEnum status;
    @NotBlank(message = "Test Listening ID không được để trống")
    private String testListeningId;

    @Override
    public String toString() {
        return "TestListeningQuestionDTO{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", serial=" + serial +
                ", answers=" + answers +
                '}';
    }
}
