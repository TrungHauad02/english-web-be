package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ReadingQuestionDTO;
import com.englishweb.english_web_be.dto.request.ReadingQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.ReadingQuestionResponseDTO;

import java.util.List;

public interface ReadingQuestionService extends BaseService<ReadingQuestionRequestDTO, ReadingQuestionResponseDTO> {

    List<ReadingQuestionResponseDTO> findAllByReadingId(String readingId);

    List<ReadingQuestionDTO> findAllDTOByReadingId(String readingId);
}
