package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.SpeakingTopicDTO;
import com.englishweb.english_web_be.model.SpeakingTopic;
import com.englishweb.english_web_be.repository.SpeakingTopicRepository;
import com.englishweb.english_web_be.util.ValidationUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class SpeakingTopicService extends BaseService<SpeakingTopic, SpeakingTopicDTO, SpeakingTopicRepository> {

    private final SpeakingService speakingService;

    public SpeakingTopicService(SpeakingTopicRepository repository, @Lazy SpeakingService speakingService) {
        super(repository);
        this.speakingService = speakingService;
    }

    public SpeakingTopicDTO findBySpeakingId(String speakingId) {
        speakingService.isExist(speakingId);
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
        dto.setSpeakingTopicId(entity.getSpeaking().getId());
        return dto;
    }

    @Override
    protected SpeakingTopic convertToEntity(SpeakingTopicDTO dto) {
        SpeakingTopic entity = new SpeakingTopic();
        entity.setId(dto.getId());
        entity.setTopic(dto.getTopic());
        entity.setDuration(dto.getDuration());
        entity.setStatus(dto.getStatus());
        entity.setSpeaking(speakingService.convertToEntity(speakingService.findById(dto.getSpeakingTopicId())));
        return entity;
    }
}
