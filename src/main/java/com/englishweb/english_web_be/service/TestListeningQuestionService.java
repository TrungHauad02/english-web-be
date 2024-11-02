package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestListeningQuestionDTO;
import com.englishweb.english_web_be.dto.request.TestListeningQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.TestListeningQuestionResponseDTO;

import java.util.List;

public interface TestListeningQuestionService extends BaseService<TestListeningQuestionRequestDTO, TestListeningQuestionResponseDTO> {

    List<TestListeningQuestionResponseDTO> findAllByTestListening_Id(String testListeningId);
    List<TestListeningQuestionDTO> findAllDTOByTestListening_Id(String testListeningId);
}
