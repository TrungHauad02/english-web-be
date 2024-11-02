package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestListeningAnswerDTO implements BaseDTO {
    private String id;
    @NotBlank(message = "Nội dung không được để trống")
    private String content;
    @NotNull(message = "Trường isCorrect không được để trống")
    private Boolean isCorrect;
    @NotNull(message = "Trạng thái không được để trống")
    private StatusEnum status;
    @NotBlank(message = "ID câu hỏi nghe không được để trống")
    private String testQuestionListeningId;
    @Override
    public String toString() {
        return "TestListeningAnswerDTO{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
