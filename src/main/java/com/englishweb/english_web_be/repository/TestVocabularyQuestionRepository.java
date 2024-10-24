package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.TestVocabularyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestVocabularyQuestionRepository extends JpaRepository<TestVocabularyQuestion, String> {
    List<TestVocabularyQuestion> findAllByTest_Id(String test_id);
}
