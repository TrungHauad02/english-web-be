package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestSpeakingQuestionDTO;
import com.englishweb.english_web_be.dto.request.TestSpeakingQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.TestSpeakingQuestionResponseDTO;

import java.util.List;

public interface TestSpeakingQuestionService extends BaseService<TestSpeakingQuestionRequestDTO, TestSpeakingQuestionResponseDTO> {

    List<TestSpeakingQuestionResponseDTO> findAllByTestSpeaking_Id (String testSpeakingId);
    List<TestSpeakingQuestionDTO> findAllDTOByTestSpeaking_Id (String testSpeakingId);
}
