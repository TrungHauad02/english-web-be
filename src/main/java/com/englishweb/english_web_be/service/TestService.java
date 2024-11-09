package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestDTO;

import com.englishweb.english_web_be.service.impl.TestServiceImpl;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface TestService extends BaseService<TestDTO> {
    Page<TestDTO> retrieveTestsByPage(int page, String type);
    List<TestDTO> retrieveTestsAllByType(String type);
    void  deleteQuestionTest(String testid, String type, String testupdateid,int serial);
    String addQuestionTest (String testid, String type, Map<String, Object> testadd );
}
