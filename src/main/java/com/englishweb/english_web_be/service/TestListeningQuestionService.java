package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestListeningQuestionDTO;

import java.util.List;

public interface TestListeningQuestionService extends BaseService<TestListeningQuestionDTO> {

    List<TestListeningQuestionDTO> findAllByTestListening_Id(String testListeningId);

}
