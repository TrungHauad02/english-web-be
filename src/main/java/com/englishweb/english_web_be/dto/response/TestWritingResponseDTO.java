package com.englishweb.english_web_be.dto.response;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestWritingResponseDTO implements BaseDTO {
    private String id;
    private int serial;
    private String content;
    private StatusEnum status;
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