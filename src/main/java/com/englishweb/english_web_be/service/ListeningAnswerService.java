package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ListeningAnswerDTO;
import com.englishweb.english_web_be.dto.request.ListeningAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.ListeningAnswerResponseDTO;

import java.util.List;

public interface ListeningAnswerService extends BaseService<ListeningAnswerRequestDTO, ListeningAnswerResponseDTO> {

    List<ListeningAnswerResponseDTO> findByQuestionId(String questionId);

    List<ListeningAnswerDTO> findDTOByQuestionId(String questionId);
}
