package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.SpeakingConversationDTO;
import com.englishweb.english_web_be.dto.SpeakingDTO;
import com.englishweb.english_web_be.dto.SpeakingTopicDTO;
import com.englishweb.english_web_be.model.Speaking;
import com.englishweb.english_web_be.repository.SpeakingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakingService extends BaseService<Speaking, SpeakingDTO, SpeakingRepository> {

    private final SpeakingConversationService speakingConversationService;
    private final SpeakingTopicService speakingTopicService;

    public SpeakingService(SpeakingRepository repository, SpeakingConversationService speakingConversationService, SpeakingTopicService speakingTopicService) {
        super(repository);
        this.speakingConversationService = speakingConversationService;
        this.speakingTopicService = speakingTopicService;
    }

    @Override
    public void delete(String id){
        List<SpeakingConversationDTO> speakingConversationDTOList = speakingConversationService.findBySpeakingId(id);
        for (SpeakingConversationDTO speakingConversationDTO : speakingConversationDTOList) {
            speakingConversationService.delete(speakingConversationDTO.getId());
        }
        SpeakingTopicDTO speakingTopicDTO = speakingTopicService.findBySpeakingId(id);
        speakingTopicService.delete(speakingTopicDTO.getId());
        super.delete(id);
    }

    @Override
    protected SpeakingDTO convertToDTO(Speaking entity) {
        SpeakingDTO dto = new SpeakingDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setImage(entity.getImage());
        dto.setSerial(entity.getSerial());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    protected Speaking convertToEntity(SpeakingDTO dto) {
        Speaking entity = new Speaking();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
        entity.setSerial(dto.getSerial());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}
