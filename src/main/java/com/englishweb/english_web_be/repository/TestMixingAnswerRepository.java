package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.TestGrammarAnswer;
import com.englishweb.english_web_be.model.TestMixingAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestMixingAnswerRepository  extends JpaRepository<TestMixingAnswer, String> {

    List<TestMixingAnswer> findAllByTestMixingQuestion_Id(String testMixingQuestionId);
}


