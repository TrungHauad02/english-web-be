package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.ReadingDTO;
import com.englishweb.english_web_be.model.Reading;
import com.englishweb.english_web_be.repository.ReadingRepository;
import com.englishweb.english_web_be.service.ReadingService;
import org.springframework.stereotype.Service;

@Service
public class ReadingServiceImpl extends BaseServiceImpl<Reading, ReadingDTO, ReadingRepository> implements ReadingService {

    public ReadingServiceImpl(ReadingRepository repository) {
        super(repository);
    }

    @Override
    protected Reading convertToEntity(ReadingDTO dto){
        Reading entity = new Reading();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setFile(dto.getFile());
        entity.setSerial(dto.getSerial());
        entity.setImage(dto.getImage());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    protected ReadingDTO convertToDTO(Reading entity) {
        ReadingDTO dto = new ReadingDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setFile(entity.getFile());
        dto.setSerial(entity.getSerial());
        dto.setImage(entity.getImage());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
