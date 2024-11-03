package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ReadingQuestionDTO;
import com.englishweb.english_web_be.dto.request.ReadingQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.ReadingQuestionResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;

import java.util.List;

public interface ReadingQuestionService extends BaseService<ReadingQuestionRequestDTO, ReadingQuestionResponseDTO> {

    List<ReadingQuestionResponseDTO> findAllByReadingId(String readingId);

    List<ReadingQuestionResponseDTO> findAllByReadingIdAndStatus(String readingId, StatusEnum status);

    List<ReadingQuestionDTO> findAllDTOByReadingId(String readingId);
}
