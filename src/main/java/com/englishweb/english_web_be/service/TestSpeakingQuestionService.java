package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestSpeakingQuestionDTO;

import java.util.List;

public interface TestSpeakingQuestionService extends BaseService<TestSpeakingQuestionDTO> {

    List<TestSpeakingQuestionDTO> findAllByTestSpeaking_Id (String testSpeakingId);
}
