package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestReadingQuestionDTO;

import java.util.List;

public interface TestReadingQuestionService extends BaseService<TestReadingQuestionDTO> {

    List<TestReadingQuestionDTO> findAllByTestReading_Id(String testReadingId);
}
