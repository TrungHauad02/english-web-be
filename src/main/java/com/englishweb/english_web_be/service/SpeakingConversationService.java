package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.SpeakingConversationDTO;
import com.englishweb.english_web_be.model.SpeakingConversation;
import com.englishweb.english_web_be.repository.SpeakingConversationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakingConversationService {
    SpeakingConversationRepository repository;

    public SpeakingConversationService(SpeakingConversationRepository repository) {
        this.repository = repository;
    }

    public List<SpeakingConversationDTO> retrieveSpeakingConversationBySpeakingId(String speakingId) {
        List<SpeakingConversation> entityList = repository.findAllBySpeaking_Id(speakingId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private SpeakingConversationDTO convertToDTO(SpeakingConversation entity) {
        SpeakingConversationDTO dto = new SpeakingConversationDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setContent(entity.getContent());
        dto.setSerial(entity.getSerial());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
