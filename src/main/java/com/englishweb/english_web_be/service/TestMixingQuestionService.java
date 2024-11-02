package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestMixingQuestionDTO;
import com.englishweb.english_web_be.modelenum.TestMixingTypeEnum;

import java.util.List;

public interface TestMixingQuestionService extends BaseService<TestMixingQuestionDTO> {

    List<TestMixingQuestionDTO> findAllByTestId(String testId);
    List<TestMixingQuestionDTO> findAllByTest_IdAndType(String testId, TestMixingTypeEnum typeEnum);

}
