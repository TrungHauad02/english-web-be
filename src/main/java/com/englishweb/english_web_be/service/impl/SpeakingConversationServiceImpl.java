package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.SpeakingConversationDTO;
import com.englishweb.english_web_be.model.SpeakingConversation;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.SpeakingConversationRepository;
import com.englishweb.english_web_be.service.SpeakingConversationService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakingConversationServiceImpl extends BaseServiceImpl<SpeakingConversation, SpeakingConversationDTO,
        SpeakingConversationRepository> implements SpeakingConversationService {

    private final SpeakingServiceImpl speakingService;

    public SpeakingConversationServiceImpl(SpeakingConversationRepository repository,
                                           @Lazy SpeakingServiceImpl speakingService) {
        super(repository);
        this.speakingService = speakingService;
    }

    @Override
    public List<SpeakingConversationDTO> findBySpeakingId(String speakingId) {
        speakingService.isExist(speakingId);
        List<SpeakingConversation> entityList = repository.findAllBySpeaking_Id(speakingId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<SpeakingConversationDTO> findBySpeakingIdAndStatus(String speakingId, StatusEnum status) {
        if(status == null)
            return findBySpeakingId(speakingId);
        List<SpeakingConversation> entityList = repository.findAllBySpeaking_IdAndStatus(speakingId, status);
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
