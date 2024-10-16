package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.SpeakingTopicDTO;
import com.englishweb.english_web_be.model.SpeakingTopic;
import com.englishweb.english_web_be.repository.SpeakingTopicRepository;
import org.springframework.stereotype.Service;

@Service
public class SpeakingTopicService extends BaseService<SpeakingTopic, SpeakingTopicDTO, SpeakingTopicRepository> {

    public SpeakingTopicService(SpeakingTopicRepository repository) {
        super(repository);
    }

    public SpeakingTopicDTO findBySpeakingId(String speakingId) {
        SpeakingTopic speakingTopic = repository.findBySpeaking_Id(speakingId);
        return convertToDTO(speakingTopic);
    }

    @Override
    protected SpeakingTopicDTO convertToDTO(SpeakingTopic entity) {
        SpeakingTopicDTO dto = new SpeakingTopicDTO();
        dto.setId(entity.getId());
        dto.setTopic(entity.getTopic());
        dto.setDuration(entity.getDuration());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    protected SpeakingTopic convertToEntity(SpeakingTopicDTO dto) {
        SpeakingTopic entity = new SpeakingTopic();
        entity.setId(dto.getId());
        entity.setTopic(dto.getTopic());
        entity.setDuration(dto.getDuration());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}
