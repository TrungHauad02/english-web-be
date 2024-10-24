package com.englishweb.english_web_be.dto;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestMixingTypeEnum;

import java.util.List;

public class TestMixingQuestionDTO implements BaseDTO {
    private String id;
    private String content;
    private int serial;
    private String explanation;
    private TestMixingTypeEnum type;
    private StatusEnum status;
    private List<TestMixingAnswerDTO> answers;
    private String testId;

    public TestMixingQuestionDTO() {
    }

    public TestMixingQuestionDTO(String id, String content, int serial, String explanation, TestMixingTypeEnum type, StatusEnum status) {
        this.id = id;
        this.content = content;
        this.serial = serial;
        this.explanation = explanation;
        this.type = type;
        this.status = status;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }



    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public TestMixingTypeEnum getType() {
        return type;
    }

    public void setType(TestMixingTypeEnum type) {
        this.type = type;
    }

    public List<TestMixingAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<TestMixingAnswerDTO> answers) {
        this.answers = answers;
    }
}