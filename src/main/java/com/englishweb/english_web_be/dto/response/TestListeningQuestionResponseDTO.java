package com.englishweb.english_web_be.dto.response;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestListeningQuestionResponseDTO implements BaseDTO {
    private String id;
    private String content;
    private int serial;
    private List<TestListeningAnswerResponseDTO> answers;
    private StatusEnum status;
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
