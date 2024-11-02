package com.englishweb.english_web_be.dto;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;

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
    @NotBlank(message = "Test type cannot be null")
    private TestTypeEnum type;
    @NotBlank(message = "Status cannot be null")
    private StatusEnum status;
    @NotEmpty(message = "Test listening questions cannot be empty")
    private List<TestListeningDTO> testListenings;
    @NotEmpty(message = "Test reading questions cannot be empty")
    private List<TestReadingDTO> testReadings;
    @NotEmpty(message = "Test writing questions cannot be empty")
    private List<TestWritingDTO> testWritings;
    @NotEmpty(message = "Test speaking questions cannot be empty")
    private List<TestSpeakingDTO> testSpeakings;
    @NotEmpty(message = "Test mixing questions cannot be empty")
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
