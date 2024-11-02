package com.englishweb.english_web_be.dto.response;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestResponseDTO implements BaseDTO {
    private String id;
    private String title;
    private int serial;
    private int duration;
    private TestTypeEnum type;
    private StatusEnum status;
    private List<TestListeningResponseDTO> testListenings;
    private List<TestReadingResponseDTO> testReadings;
    private List<TestWritingResponseDTO> testWritings;
    private List<TestSpeakingResponseDTO>  testSpeakings;
    private List<TestMixingQuestionResponseDTO> testMixingQuestions;

    @Override
    public String toString() {
        return "TestDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", serial=" + serial +
                ", duration=" + duration +
                ", type=" + type +
                ", status=" + status +
                ", testListenings=" + (testListenings != null ? testListenings.toString() : "null") +
                ", testReadings=" + (testReadings != null ? testReadings.toString() : "null") +
                ", testWritings=" + (testWritings != null ? testWritings.toString() : "null") +
                ", testSpeaking=" + (testSpeakings != null ? testListenings.toString() : "null") +
                '}';
    }
}
