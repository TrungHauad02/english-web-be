package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestReadingDTO;

import java.util.List;

public interface TestReadingService extends BaseService<TestReadingDTO > {

    List<TestReadingDTO> findAllByTestId(String test_id);

}
