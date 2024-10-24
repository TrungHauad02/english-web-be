package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.TestGrammarQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestGrammarQuestionRepository  extends JpaRepository<TestGrammarQuestion, String> {
    List<TestGrammarQuestion> findAllByTest_Id(String testId);
}
