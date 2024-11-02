package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ReadingAnswerDTO;
import com.englishweb.english_web_be.dto.request.ReadingAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.ReadingAnswerResponseDTO;

import java.util.List;

public interface ReadingAnswerService extends BaseService<ReadingAnswerRequestDTO, ReadingAnswerResponseDTO> {

    List<ReadingAnswerResponseDTO> findAllByQuestionId(String questionId);

    List<ReadingAnswerDTO> findAllDTOByQuestionId(String questionId);
}
