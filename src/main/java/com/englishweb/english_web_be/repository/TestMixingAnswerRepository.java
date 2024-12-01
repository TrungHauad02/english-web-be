package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.TestMixingAnswer;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestMixingAnswerRepository  extends JpaRepository<TestMixingAnswer, String> {

    List<TestMixingAnswer> findAllByTestMixingQuestion_Id(String testMixingQuestionId);
    List<TestMixingAnswer> findAllByTestMixingQuestion_IdAndStatus(String testMixingQuestionId, StatusEnum status);
}


