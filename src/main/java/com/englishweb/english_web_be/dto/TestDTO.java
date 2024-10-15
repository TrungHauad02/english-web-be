package com.englishweb.english_web_be.dto;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;

import java.util.List;

public class TestDTO {
    private String id;
    private String title;
    private int serial;
    private int duration;
    private TestTypeEnum type;
    private StatusEnum status;
    private List<TestListeningDTO> testListenings;
    private List<TestReadingDTO> testReadings;
    private List<TestVocabularyQuestionDTO> testVocabularyQuestions;
    private List<TestGrammarQuestionDTO> testGrammarQuestions;
    private List<TestWritingDTO> testWritings;
    private TestSpeakingDTO testSpeaking;

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

    public TestDTO(String id, String title, int serial, int duration, TestTypeEnum type, StatusEnum status, List<TestListeningDTO> testListenings, List<TestReadingDTO> testReadings, TestSpeakingDTO testSpeaking, List<TestWritingDTO> testWritings, List<TestVocabularyQuestionDTO> testVocabularyQuestions, List<TestGrammarQuestionDTO> testGrammarQuestions) {

            this.id = id;
            this.title = title;
            this.serial = serial;
            this.duration = duration;
            this.type = type;
            this.status = status;
            this.testListenings = testListenings;
            this.testReadings = testReadings;
            this.testVocabularyQuestions = testVocabularyQuestions;
            this.testGrammarQuestions = testGrammarQuestions;
            this.testWritings = testWritings;
            this.testSpeaking = testSpeaking;
    }

    // Getters and Setters
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

    public List<TestVocabularyQuestionDTO> getTestVocabularyQuestions() {
        return testVocabularyQuestions;
    }

    public void setTestVocabularyQuestions(List<TestVocabularyQuestionDTO> testVocabularyQuestions) {
        this.testVocabularyQuestions = testVocabularyQuestions;
    }

    public List<TestGrammarQuestionDTO> getTestGrammarQuestions() {
        return testGrammarQuestions;
    }

    public void setTestGrammarQuestions(List<TestGrammarQuestionDTO> testGrammarQuestions) {
        this.testGrammarQuestions = testGrammarQuestions;
    }

    public List<TestWritingDTO> getTestWritings() {
        return testWritings;
    }

    public void setTestWritings(List<TestWritingDTO> testWritings) {
        this.testWritings = testWritings;
    }

    public TestSpeakingDTO getTestSpeaking() {
        return testSpeaking;
    }

    public void setTestSpeaking(TestSpeakingDTO testSpeaking) {
        this.testSpeaking = testSpeaking;
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
                ", testVocabularyQuestions=" + (testVocabularyQuestions != null ? testVocabularyQuestions.toString() : "null") +
                ", testGrammarQuestions=" + (testGrammarQuestions != null ? testGrammarQuestions.toString() : "null") +
                ", testWritings=" + (testWritings != null ? testWritings.toString() : "null") +
                ", testSpeaking=" + (testSpeaking != null ? testSpeaking.toString() : "null") +
                '}';
    }

}
