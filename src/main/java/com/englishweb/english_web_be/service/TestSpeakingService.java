package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestSpeakingDTO;

import java.util.List;

public interface TestSpeakingService extends BaseService<TestSpeakingDTO> {

    List<TestSpeakingDTO> findAllByTest_Id(String test_id);
}
