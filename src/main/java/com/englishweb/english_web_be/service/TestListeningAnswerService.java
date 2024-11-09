package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestListeningAnswerDTO;
import com.englishweb.english_web_be.dto.request.TestListeningAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.TestListeningAnswerResponseDTO;
import com.englishweb.english_web_be.model.TestListeningAnswer;

import java.util.List;

public interface TestListeningAnswerService extends BaseService<TestListeningAnswerDTO> {

    List<TestListeningAnswerDTO> findAllByQuestionId(String questionId);
}
