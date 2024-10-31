package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TestService extends BaseService<TestDTO> {

    Page<TestDTO> retrieveTestsByPage(int page, String type);
    List<TestDTO> retrieveTestsAllByType(String type);
}
