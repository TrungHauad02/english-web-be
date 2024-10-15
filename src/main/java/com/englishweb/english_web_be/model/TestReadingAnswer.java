package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TestReadingAnswer {
    @Id
    private String id;
    private String content;
    private Boolean isCorrect;
    private StatusEnum status;
    @ManyToOne
    @JoinColumn(name = "test_reading_question_id")
    private TestReadingQuestion testReadingQuestion;

    public TestReadingAnswer() {
    }

    public TestReadingAnswer(String id, String content, Boolean isCorrect, StatusEnum status) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
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

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean correct) {
        isCorrect = correct;
    }


    public TestReadingQuestion getTestReadingQuestion() {
        return testReadingQuestion;
    }

    public void setTestReadingQuestion(TestReadingQuestion testReadingQuestion) {
        this.testReadingQuestion = testReadingQuestion;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
