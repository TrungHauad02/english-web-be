package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestWritingDTO;

import java.util.List;

public interface TestWritingService extends BaseService<TestWritingDTO> {

    List<TestWritingDTO> findAllByTestId(String test_id);
}
