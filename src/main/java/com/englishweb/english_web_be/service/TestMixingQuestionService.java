package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestMixingQuestionDTO;
import com.englishweb.english_web_be.dto.request.TestMixingQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.TestMixingQuestionResponseDTO;
import com.englishweb.english_web_be.modelenum.TestMixingTypeEnum;

import java.util.List;

public interface TestMixingQuestionService extends BaseService<TestMixingQuestionRequestDTO, TestMixingQuestionResponseDTO> {

    List<TestMixingQuestionResponseDTO> findAllByTestId(String testId);
    List<TestMixingQuestionDTO> findAllDTOByTestId(String testId);
    List<TestMixingQuestionResponseDTO> findAllByTest_IdAndType(String testId, TestMixingTypeEnum typeEnum);
    List<TestMixingQuestionDTO> findAllDTOByTest_IdAndType(String testId, TestMixingTypeEnum typeEnum);

}
