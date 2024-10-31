package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.WritingDTO;
import com.englishweb.english_web_be.model.Writing;
import com.englishweb.english_web_be.repository.WritingRepository;
import com.englishweb.english_web_be.service.WritingService;
import org.springframework.stereotype.Service;

@Service
public class WritingServiceImpl extends BaseServiceImpl<Writing, WritingDTO, WritingRepository> implements WritingService {

    public WritingServiceImpl(WritingRepository repository) {
        super(repository);
    }

    @Override
    protected WritingDTO convertToDTO(Writing entity) {
        WritingDTO dto = new WritingDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setImage(entity.getImage());
        dto.setSerial(entity.getSerial());
        dto.setTitle(entity.getTitle());
        dto.setStatus(entity.getStatus());
        dto.setTopic(entity.getTopic());
        return dto;
    }

    @Override
    protected Writing convertToEntity(WritingDTO dto) {
        Writing entity = new Writing();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
        entity.setSerial(dto.getSerial());
        entity.setTitle(dto.getTitle());
        entity.setStatus(dto.getStatus());
        entity.setTopic(dto.getTopic());
        return entity;
    }
}
