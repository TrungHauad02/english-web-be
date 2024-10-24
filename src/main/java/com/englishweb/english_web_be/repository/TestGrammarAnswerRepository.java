package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.TestGrammarAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestGrammarAnswerRepository extends JpaRepository<TestGrammarAnswer, String> {

    List <TestGrammarAnswer> findAllByTestGrammarQuestion_Id(String testGrammarQuestionId);
}
