package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestSpeakingQuestionDTO implements BaseDTO {
    private String id;
    @NotBlank(message = "Nội dung không được để trống")
    private String content;
    @Positive(message = "Serial phải là số dương")
    private int serial;

    private StatusEnum status;

    private String testSpeakingId;

    @Override
    public String toString() {
        return "TestSpeakingQuestionDTO{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", serial=" + serial +
                ", status=" + status +
                '}';
    }
}
