package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TopicQuestionDTO;

import java.util.List;

public interface TopicQuestionService extends BaseService<TopicQuestionDTO> {

    List<TopicQuestionDTO> findAllByTopicId(String topicId);
}
