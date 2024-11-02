package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.SpeakingConversationDTO;
import com.englishweb.english_web_be.dto.request.SpeakingConversationRequestDTO;
import com.englishweb.english_web_be.dto.response.SpeakingConversationResponseDTO;
import com.englishweb.english_web_be.mapper.SpeakingConversationMapper;
import com.englishweb.english_web_be.model.SpeakingConversation;
import com.englishweb.english_web_be.repository.SpeakingConversationRepository;
import com.englishweb.english_web_be.service.SpeakingConversationService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakingConversationServiceImpl extends BaseServiceImpl<SpeakingConversation, SpeakingConversationDTO, SpeakingConversationRequestDTO, SpeakingConversationResponseDTO, SpeakingConversationMapper, SpeakingConversationRepository> implements SpeakingConversationService {

    private final SpeakingServiceImpl speakingService;

    public SpeakingConversationServiceImpl(SpeakingConversationRepository repository,
                                           @Lazy SpeakingServiceImpl speakingService,
                                           @Lazy SpeakingConversationMapper mapper) {
        super(repository, mapper);
        this.speakingService = speakingService;
    }

    @Override
    public List<SpeakingConversationResponseDTO> findBySpeakingId(String speakingId) {
        speakingService.isExist(speakingId);
        List<SpeakingConversation> entityList = repository.findAllBySpeaking_Id(speakingId);
        return entityList.stream()
                .map(this::convertToDTO)
                .map(mapper::mapToResponseDTO)
                .toList();
    }

    @Override
    public List<SpeakingConversationDTO> findAllDTOBySpeakingId(String speakingId) {
        speakingService.isExist(speakingId);
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
        entity.setSpeaking(speakingService.convertToEntity(speakingService.findDTOById(dto.getSpeakingId())));
        return entity;
    }
}
