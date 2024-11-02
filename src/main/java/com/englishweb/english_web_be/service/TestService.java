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
    void  deleteQuestionTest(String testid, String type, String testupdateid,int serial);
    void addQuestionTest (String testid, String type, Map<String, Object> testadd );
}
