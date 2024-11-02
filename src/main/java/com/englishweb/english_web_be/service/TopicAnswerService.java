package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TopicAnswerDTO;
import com.englishweb.english_web_be.dto.request.TopicAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.TopicAnswerResponseDTO;

import java.util.List;

public interface TopicAnswerService extends BaseService<TopicAnswerRequestDTO, TopicAnswerResponseDTO> {

    List<TopicAnswerResponseDTO> findAllByQuestionId(String questionId);

    List<TopicAnswerDTO> findAllDTOByQuestionId(String questionId);
}
