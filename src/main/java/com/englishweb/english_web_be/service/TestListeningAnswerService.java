package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestListeningAnswerDTO;

import java.util.List;

public interface TestListeningAnswerService extends BaseService<TestListeningAnswerDTO> {

    List<TestListeningAnswerDTO> findAllByQuestionId(String questionId);
}
