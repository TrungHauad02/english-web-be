package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TestListeningAnswer {
    @Id
    private String id;
    private String content;
    private Boolean isCorrect;
    StatusEnum status;
    @ManyToOne
    @JoinColumn(name = "test_listening_question_id")
    private TestListeningQuestion TestListeningQuestion;

    public TestListeningAnswer() {
    }

    public TestListeningAnswer(String id, String content, Boolean isCorrect, StatusEnum status) {
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

    public TestListeningQuestion getTestListeningQuestion() {
        return TestListeningQuestion;
    }

    public void setTestListeningQuestion(TestListeningQuestion testListeningQuestion) {
        TestListeningQuestion = testListeningQuestion;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
