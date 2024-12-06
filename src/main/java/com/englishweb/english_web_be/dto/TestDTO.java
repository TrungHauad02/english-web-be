package com.englishweb.english_web_be.dto;
import com.englishweb.english_web_be.model.SubmitTest;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestDTO implements BaseDTO {
    private String id;
    @NotBlank(message = "Title cannot be empty")
    private String title;
    @Positive(message = "Serial number must be greater than 0")
    private int serial;
    @Positive(message = "Duration must be greater than 0")
    private int duration;

    private TestTypeEnum type;

    private int numberOfQuestions;

    private BigDecimal scoreLastOfTest;

    private StatusEnum status;

    private List<TestListeningDTO> testListenings;

    private List<TestReadingDTO> testReadings;

    private List<TestWritingDTO> testWritings;

    private List<TestSpeakingDTO> testSpeakings;


    private List<TestMixingQuestionDTO> testMixingQuestions;

    private List<String> submitTestsId;


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
