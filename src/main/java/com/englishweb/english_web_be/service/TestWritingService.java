package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestWritingDTO;
import com.englishweb.english_web_be.dto.request.TestWritingRequestDTO;
import com.englishweb.english_web_be.dto.response.TestWritingResponseDTO;

import java.util.List;

public interface TestWritingService extends BaseService<TestWritingRequestDTO, TestWritingResponseDTO> {

    List<TestWritingResponseDTO> findAllByTestId(String test_id);
    List<TestWritingDTO> findAllDTOByTestId(String test_id);
}
