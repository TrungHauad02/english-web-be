package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestReadingAnswerDTO;

import java.util.List;

public interface TestReadingAnswerService extends BaseService<TestReadingAnswerDTO> {

    List<TestReadingAnswerDTO> findAllByQuestionId(String questionId);

}
