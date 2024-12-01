package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.SubmitTestDTO;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface SubmitTestService extends BaseService<SubmitTestDTO> {
     Page<SubmitTestDTO> findSubmitTestsBySpecification(String title, TestTypeEnum type, int page, int size, String startDate, String endDate,String userId);
}
