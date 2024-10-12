package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TopicQuestionDTO;
import com.englishweb.english_web_be.model.TopicQuestion;
import com.englishweb.english_web_be.repository.TopicQuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class TopicQuestionService {
    TopicQuestionRepository repository;
    TopicAnswerService topicAnswerService;

    public TopicQuestionService(TopicQuestionRepository repository, TopicAnswerService topicAnswerService) {
        this.repository = repository;
        this.topicAnswerService = topicAnswerService;
    }

    private TopicQuestionDTO convertToDto(TopicQuestion topicQuestion) {
        TopicQuestionDTO dto = new TopicQuestionDTO();
        dto.setId(topicQuestion.getId());
        dto.setContent(topicQuestion.getContent());
        dto.setSerial(topicQuestion.getSerial());
        dto.setExplanation(topicQuestion.getExplanation());
        dto.setStatus(topicQuestion.getStatus());
        dto.setTopicId(topicQuestion.getTopic().getId());
        dto.setAnswers(topicAnswerService.findAllByQuestionId(topicQuestion.getId()));
        return dto;
    }
}
