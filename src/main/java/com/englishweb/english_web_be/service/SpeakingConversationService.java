package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.SpeakingConversationDTO;
import com.englishweb.english_web_be.model.SpeakingConversation;
import com.englishweb.english_web_be.repository.SpeakingConversationRepository;
import com.englishweb.english_web_be.util.ValidationUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakingConversationService extends BaseService<SpeakingConversation, SpeakingConversationDTO, SpeakingConversationRepository> {
    private final SpeakingService speakingService;

    public SpeakingConversationService(SpeakingConversationRepository repository, @Lazy SpeakingService speakingService) {
        super(repository);
        this.speakingService = speakingService;
    }

    public List<SpeakingConversationDTO> findBySpeakingId(String speakingId) {
        ValidationUtils.getInstance().validateExistId(speakingService.repository, speakingId);
        List<SpeakingConversation> entityList = repository.findAllBySpeaking_Id(speakingId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected SpeakingConversationDTO convertToDTO(SpeakingConversation entity) {
        SpeakingConversationDTO dto = new SpeakingConversationDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setContent(entity.getContent());
        dto.setSerial(entity.getSerial());
        dto.setStatus(entity.getStatus());
        dto.setSpeakingId(entity.getSpeaking().getId());
        return dto;
    }

    @Override
    protected SpeakingConversation convertToEntity(SpeakingConversationDTO dto) {
        SpeakingConversation entity = new SpeakingConversation();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setContent(dto.getContent());
        entity.setSerial(dto.getSerial());
        entity.setStatus(dto.getStatus());
        entity.setSpeaking(speakingService.convertToEntity(speakingService.findById(dto.getSpeakingId())));
        return entity;
    }
}
