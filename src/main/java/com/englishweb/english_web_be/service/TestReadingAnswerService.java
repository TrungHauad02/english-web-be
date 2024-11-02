package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestReadingAnswerDTO;
import com.englishweb.english_web_be.dto.request.TestReadingAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.TestReadingAnswerResponseDTO;

import java.util.List;

public interface TestReadingAnswerService extends BaseService<TestReadingAnswerRequestDTO, TestReadingAnswerResponseDTO> {

    List<TestReadingAnswerResponseDTO> findAllByQuestionId(String questionId);
    List<TestReadingAnswerDTO> findAllDTOByQuestionId(String questionId);
}
