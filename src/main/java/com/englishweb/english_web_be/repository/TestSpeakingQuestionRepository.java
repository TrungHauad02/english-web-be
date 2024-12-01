package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.TestSpeakingQuestion;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestSpeakingQuestionRepository extends JpaRepository<TestSpeakingQuestion, String> {
    List<TestSpeakingQuestion> findAllByTestSpeaking_Id(String testSpeakingId);
    List<TestSpeakingQuestion> findAllByTestSpeaking_IdAndStatus(String testSpeakingId, StatusEnum status);
}
