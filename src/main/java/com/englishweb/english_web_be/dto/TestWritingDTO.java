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
public class TestWritingDTO implements BaseDTO {
    private String id;
    @Positive(message = "Serial phải là số dương")
    private int serial;
    @NotBlank(message = "Nội dung không được để trống")
    private String content;
    @NotNull(message = "Trạng thái không được để trống")
    private StatusEnum status;
    @NotBlank(message = "Test ID không được để trống")
    private String testId;


    @Override
    public String toString() {
        return "TestWritingDTO{" +
                "id='" + id + '\'' +
                ", serial=" + serial +
                ", content='" + content + '\'' +
                ", statusEnum=" + status +
                '}';
    }
}
