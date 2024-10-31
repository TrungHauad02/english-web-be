package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestMixingAnswerDTO;

import java.util.List;

public interface TestMixingAnswerService extends BaseService<TestMixingAnswerDTO> {

    List<TestMixingAnswerDTO> findAllByQuestionId(String questionId);
}
