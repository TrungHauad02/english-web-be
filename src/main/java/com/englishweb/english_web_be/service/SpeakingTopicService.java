package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.SpeakingTopicDTO;
import com.englishweb.english_web_be.model.SpeakingTopic;
import com.englishweb.english_web_be.repository.SpeakingTopicRepository;
import org.springframework.stereotype.Service;

@Service
public class SpeakingTopicService {
    SpeakingTopicRepository repository;

    public SpeakingTopicService(SpeakingTopicRepository repository) {
        this.repository = repository;
    }

    public SpeakingTopicDTO retrieveSpeakingTopicBySpeakingId(String speakingId) {
        SpeakingTopic speakingTopic = repository.findById(speakingId).get();
        return convertToDTO(speakingTopic);
    }

    private SpeakingTopicDTO convertToDTO(SpeakingTopic entity) {
        SpeakingTopicDTO dto = new SpeakingTopicDTO();
        dto.setId(entity.getId());
        dto.setTopic(entity.getTopic());
        dto.setDuration(entity.getDuration());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
