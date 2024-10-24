package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.TestVocabularyAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestVocabularyAnswerRepository extends JpaRepository<TestVocabularyAnswer,String> {
    List<TestVocabularyAnswer> findAllByTestVocabularyQuestion_Id(String question_id);
}
