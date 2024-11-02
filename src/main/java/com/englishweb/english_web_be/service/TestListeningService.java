package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestListeningDTO;
import com.englishweb.english_web_be.dto.request.TestListeningRequestDTO;
import com.englishweb.english_web_be.dto.response.TestListeningResponseDTO;

import java.util.List;

public interface TestListeningService extends BaseService<TestListeningRequestDTO, TestListeningResponseDTO> {

    List<TestListeningResponseDTO> findAllByTestId(String test_id);
    List<TestListeningDTO> findAllDTOByTestId(String test_id);
}
