package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestReadingDTO;
import com.englishweb.english_web_be.dto.request.TestReadingRequestDTO;
import com.englishweb.english_web_be.dto.response.TestReadingResponseDTO;

import java.util.List;

public interface TestReadingService extends BaseService<TestReadingRequestDTO, TestReadingResponseDTO> {

    List<TestReadingResponseDTO> findAllByTestId(String test_id);
    List<TestReadingDTO> findAllDTOByTestId(String test_id);
}
