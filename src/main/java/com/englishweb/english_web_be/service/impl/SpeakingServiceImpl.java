package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.SpeakingDTO;
import com.englishweb.english_web_be.dto.SpeakingConversationDTO;
import com.englishweb.english_web_be.model.Speaking;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.SpeakingRepository;
import com.englishweb.english_web_be.service.SpeakingConversationService;
import com.englishweb.english_web_be.service.SpeakingService;
import com.englishweb.english_web_be.util.ValidationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakingServiceImpl extends BaseServiceImpl<Speaking, SpeakingDTO, SpeakingRepository>
        implements SpeakingService {

    private final SpeakingConversationService speakingConversationService;

    public SpeakingServiceImpl(SpeakingRepository repository,
                               SpeakingConversationService speakingConversationService) {
        super(repository);
        this.speakingConversationService = speakingConversationService;
    }

    @Override
    public Page<SpeakingDTO> findSpeakingWithStatusAndPagingAndSorting(StatusEnum status, int page, int size, String sortBy, String sortDir, Class<SpeakingDTO> dtoClass) {
        if(status == null) {
            return super.findByPage(page, size, sortBy, sortDir, dtoClass);
        }

        ValidationUtils.getInstance().validatePageRequestParam(page, size, sortBy, dtoClass);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAllSpeakingByStatus(status, pageable)
                .map(this::convertToDTO);
    }

    @Override
    public void delete(String id) {
        List<SpeakingConversationDTO> speakingConversationDTOList = speakingConversationService.findBySpeakingId(id);
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
