package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestDTO;
import com.englishweb.english_web_be.dto.request.TestRequestDTO;
import com.englishweb.english_web_be.dto.response.TestResponseDTO;
import com.englishweb.english_web_be.service.impl.TestServiceImpl;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface TestService extends BaseService<TestRequestDTO, TestResponseDTO> {
    Page<TestResponseDTO> retrieveTestsByPage(int page, String type);
    List<TestResponseDTO> retrieveTestsAllByType(String type);
    void  deleteQuestionTest(TestRequestDTO testRequestDTO, String type, String testupdateid,int serial);
    String addQuestionTest (TestRequestDTO testRequestDTO, String type, Map<String, Object> testadd );
}
