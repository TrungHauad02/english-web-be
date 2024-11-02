package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestSpeakingDTO;
import com.englishweb.english_web_be.dto.request.TestSpeakingRequestDTO;
import com.englishweb.english_web_be.dto.response.TestSpeakingResponseDTO;

import java.util.List;

public interface TestSpeakingService extends BaseService<TestSpeakingRequestDTO, TestSpeakingResponseDTO> {

    List<TestSpeakingResponseDTO> findAllByTest_Id(String test_id);
    List<TestSpeakingDTO> findAllDTOByTest_Id(String test_id);
}
