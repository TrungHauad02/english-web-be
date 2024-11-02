package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestReadingQuestionDTO;
import com.englishweb.english_web_be.dto.request.TestReadingQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.TestReadingQuestionResponseDTO;

import java.util.List;

public interface TestReadingQuestionService extends BaseService<TestReadingQuestionRequestDTO, TestReadingQuestionResponseDTO> {

    List<TestReadingQuestionResponseDTO> findAllByTestReading_Id(String testReadingId);
    List<TestReadingQuestionDTO> findAllDTOByTestReading_Id(String testReadingId);
}
