package com.englishweb.english_web_be.model;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TestGrammarAnswer {
    @Id
    private String id;
    private String content;
    private Boolean isCorrect;
    StatusEnum status;
    @ManyToOne
    @JoinColumn(name = "test_grammar_question_id")
    private TestGrammarQuestion testGrammarQuestion;

    public TestGrammarAnswer() {
    }

    public TestGrammarAnswer(String id, String content, Boolean isCorrect, StatusEnum status) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
        this.status = status;
    }

    public TestGrammarAnswer(String id, String content, Boolean isCorrect, StatusEnum status, com.englishweb.english_web_be.model.TestGrammarQuestion TestGrammarQuestion) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
        this.status = status;
        TestGrammarQuestion = TestGrammarQuestion;
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

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public TestGrammarQuestion getTestGrammarQuestion() {
        return testGrammarQuestion;
    }

    public void setTestGrammarQuestion(TestGrammarQuestion testGrammarQuestion) {
        this.testGrammarQuestion = testGrammarQuestion;
    }
}
