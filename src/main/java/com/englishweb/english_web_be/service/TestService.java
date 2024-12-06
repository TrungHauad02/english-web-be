package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestDTO;

import com.englishweb.english_web_be.dto.UserDTO;
import com.englishweb.english_web_be.model.Test;
import com.englishweb.english_web_be.modelenum.LevelEnum;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import com.englishweb.english_web_be.service.impl.TestServiceImpl;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TestService extends BaseService<TestDTO> {
    Page<TestDTO> findTestsBySpecification(String title, TestTypeEnum type, int page, int size, StatusEnum status , String userId,String sortDirection);
    TestDTO findByIdAndStatus(String id, StatusEnum status);
    boolean updateStatus(String id);
    Integer getMaxSerial();
}
