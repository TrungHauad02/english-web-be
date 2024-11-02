package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TopicQuestionDTO;
import com.englishweb.english_web_be.dto.request.TopicQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.TopicQuestionResponseDTO;

import java.util.List;

public interface TopicQuestionService extends BaseService<TopicQuestionRequestDTO, TopicQuestionResponseDTO> {

    List<TopicQuestionResponseDTO> findAllByTopicId(String topicId);

    List<TopicQuestionDTO> findAllDTOByTopicId(String topicId);
}
