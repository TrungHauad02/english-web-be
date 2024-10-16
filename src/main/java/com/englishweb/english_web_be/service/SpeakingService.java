package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.SpeakingDTO;
import com.englishweb.english_web_be.model.Speaking;
import com.englishweb.english_web_be.repository.SpeakingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SpeakingService extends BaseService<Speaking, SpeakingDTO, SpeakingRepository> {

    public SpeakingService(SpeakingRepository repository) {
        super(repository);
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
