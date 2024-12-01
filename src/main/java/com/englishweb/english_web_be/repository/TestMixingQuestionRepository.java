package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.TestMixingQuestion;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestMixingTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestMixingQuestionRepository extends JpaRepository<TestMixingQuestion, String> {

    List<TestMixingQuestion> findAllByTest_Id(String testId);
    List<TestMixingQuestion> findAllByTest_IdAndType(String testId, TestMixingTypeEnum type);
    List<TestMixingQuestion> findAllByTest_IdAndStatus(String testId, StatusEnum status);

}
