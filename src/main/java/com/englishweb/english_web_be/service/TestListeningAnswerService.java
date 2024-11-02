package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestListeningAnswerDTO;
import com.englishweb.english_web_be.dto.request.TestListeningAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.TestListeningAnswerResponseDTO;

import java.util.List;

public interface TestListeningAnswerService extends BaseService<TestListeningAnswerRequestDTO, TestListeningAnswerResponseDTO> {

    List<TestListeningAnswerResponseDTO> findAllByQuestionId(String questionId);
    List<TestListeningAnswerDTO> findAllDTOByQuestionId(String questionId);
}
