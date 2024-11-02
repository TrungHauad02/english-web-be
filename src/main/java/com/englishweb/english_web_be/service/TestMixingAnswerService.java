package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestMixingAnswerDTO;
import com.englishweb.english_web_be.dto.request.TestMixingAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.TestMixingAnswerResponseDTO;

import java.util.List;

public interface TestMixingAnswerService extends BaseService<TestMixingAnswerRequestDTO, TestMixingAnswerResponseDTO> {

    List<TestMixingAnswerResponseDTO> findAllByQuestionId(String questionId);
    List<TestMixingAnswerDTO> findAllDTOByQuestionId(String questionId);
}
