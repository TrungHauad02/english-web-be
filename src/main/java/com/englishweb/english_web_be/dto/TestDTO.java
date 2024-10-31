package com.englishweb.english_web_be.dto;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestDTO implements BaseDTO {
    private String id;
    private String title;
    private int serial;
    private int duration;
    private TestTypeEnum type;
    private StatusEnum status;
    private List<TestListeningDTO> testListenings;
    private List<TestReadingDTO> testReadings;
    private List<TestWritingDTO> testWritings;
    private List<TestSpeakingDTO>  testSpeakings;
    private List<TestMixingQuestionDTO> testMixingQuestions;

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
