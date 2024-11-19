package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestDTO;

import com.englishweb.english_web_be.dto.UserDTO;
import com.englishweb.english_web_be.modelenum.LevelEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import com.englishweb.english_web_be.service.impl.TestServiceImpl;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TestService extends BaseService<TestDTO> {
    Page<TestDTO> retrieveTestsByPage(int page, String type);
    List<TestDTO> retrieveTestsAllByType(String type);
    Page<TestDTO> findTestsBySpecification(String title, TestTypeEnum type,  int page, int size);


}
