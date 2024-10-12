package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TopicAnswerDTO;
import com.englishweb.english_web_be.model.TopicAnswer;
import com.englishweb.english_web_be.repository.TopicAnswerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicAnswerService {
    TopicAnswerRepository repository;

    public TopicAnswerService(TopicAnswerRepository repository) {
        this.repository = repository;
    }

    public List<TopicAnswerDTO> findAllByQuestionId(String questionId) {
        List<TopicAnswer> list = repository.findAllByQuestion_Id(questionId);
        return list.stream()
                .map(this::convertToDto)  // Convert each entity to DTO
                .collect(Collectors.toList());  // Collect as a list
    }

    private TopicAnswerDTO convertToDto(TopicAnswer entity) {
        TopicAnswerDTO dto = new TopicAnswerDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setCorrect(entity.isCorrect());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
