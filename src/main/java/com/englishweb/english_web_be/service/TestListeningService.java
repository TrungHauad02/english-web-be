package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestListeningDTO;

import java.util.List;

public interface TestListeningService extends BaseService< TestListeningDTO> {

    List<TestListeningDTO> findAllByTestId(String test_id);

}
