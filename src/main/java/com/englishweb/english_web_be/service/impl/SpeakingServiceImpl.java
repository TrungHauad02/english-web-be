package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.SpeakingDTO;
import com.englishweb.english_web_be.dto.SpeakingConversationDTO;
import com.englishweb.english_web_be.dto.request.SpeakingRequestDTO;
import com.englishweb.english_web_be.dto.response.SpeakingResponseDTO;
import com.englishweb.english_web_be.mapper.SpeakingMapper;
import com.englishweb.english_web_be.model.Speaking;
import com.englishweb.english_web_be.repository.SpeakingRepository;
import com.englishweb.english_web_be.service.SpeakingConversationService;
import com.englishweb.english_web_be.service.SpeakingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakingServiceImpl extends BaseServiceImpl<Speaking, SpeakingDTO, SpeakingRequestDTO,
        SpeakingResponseDTO, SpeakingMapper, SpeakingRepository>
        implements SpeakingService {

    private final SpeakingConversationService speakingConversationService;

    public SpeakingServiceImpl(SpeakingRepository repository, SpeakingMapper mapper, SpeakingConversationService speakingConversationService) {
        super(repository, mapper);
        this.speakingConversationService = speakingConversationService;
    }

    @Override
    public void delete(String id) {
        List<SpeakingConversationDTO> speakingConversationDTOList = speakingConversationService.findAllDTOBySpeakingId(id);
        for (SpeakingConversationDTO speakingConversationDTO : speakingConversationDTOList) {
            speakingConversationService.delete(speakingConversationDTO.getId());
        }
        super.delete(id);
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
        entity.setTopic(dto.getTopic());
        entity.setDuration(dto.getDuration());
        return entity;
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
        dto.setTopic(entity.getTopic());
        dto.setDuration(entity.getDuration());
        return dto;
    }
}
