package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.TestMixingQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestMixingQuestionRepository extends JpaRepository<TestMixingQuestion, String> {

    List<TestMixingQuestion> findAllByTest_Id(String testId);
}