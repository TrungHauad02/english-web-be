package com.englishweb.english_web_be.dto;
import com.englishweb.english_web_be.model.TestMixingQuestion;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;

import java.util.List;

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

    public TestDTO() {
    }

    public TestDTO(String id, String title, int serial, int duration, TestTypeEnum type, StatusEnum status) {
        this.id = id;
        this.title = title;
        this.serial = serial;
        this.duration = duration;
        this.type = type;
        this.status = status;
    }
    public TestDTO(String id, String title, int serial, int duration, TestTypeEnum type, StatusEnum status, List<TestListeningDTO> testListenings) {
        this.id = id;
        this.title = title;
        this.serial = serial;
        this.duration = duration;
        this.type = type;
        this.status = status;
        this.testListenings = testListenings;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public TestTypeEnum getType() {
        return type;
    }

    public void setType(TestTypeEnum type) {
        this.type = type;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public List<TestListeningDTO> getTestListenings() {
        return testListenings;
    }

    public void setTestListenings(List<TestListeningDTO> testListenings) {
        this.testListenings = testListenings;
    }

    public List<TestReadingDTO> getTestReadings() {
        return testReadings;
    }

    public void setTestReadings(List<TestReadingDTO> testReadings) {
        this.testReadings = testReadings;
    }

    public List<TestWritingDTO> getTestWritings() {
        return testWritings;
    }

    public void setTestWritings(List<TestWritingDTO> testWritings) {
        this.testWritings = testWritings;
    }

    public List<TestSpeakingDTO> getTestSpeakings() {
        return testSpeakings;
    }

    public void setTestSpeakings(List<TestSpeakingDTO> testSpeakings) {
        this.testSpeakings = testSpeakings;
    }

    public List<TestMixingQuestionDTO> getTestMixingQuestions() {
        return testMixingQuestions;
    }

    public void setTestMixingQuestions(List<TestMixingQuestionDTO> testMixingQuestions) {
        this.testMixingQuestions = testMixingQuestions;
    }

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
