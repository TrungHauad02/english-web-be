package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.GrammarAnswerDTO;
import com.englishweb.english_web_be.dto.request.GrammarAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.GrammarAnswerResponseDTO;

import java.util.List;

public interface GrammarAnswerService extends BaseService<GrammarAnswerRequestDTO, GrammarAnswerResponseDTO> {

    List<GrammarAnswerResponseDTO> findAllByQuestionId(String questionId);

    List<GrammarAnswerDTO> findAllDTOByQuestionId(String questionId);
}
