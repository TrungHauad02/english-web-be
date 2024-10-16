package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ListeningDTO;
import com.englishweb.english_web_be.model.Listening;
import com.englishweb.english_web_be.repository.ListeningRepository;
import org.springframework.stereotype.Service;

@Service
public class ListeningService extends BaseService<Listening, ListeningDTO, ListeningRepository> {

    public ListeningService(ListeningRepository repository) {
        super(repository);
    }

    @Override
    protected ListeningDTO convertToDTO(Listening entity){
        ListeningDTO dto = new ListeningDTO();
        dto.setId(entity.getId());
        dto.setSerial(entity.getSerial());
        dto.setImage(entity.getImage());
        dto.setDescription(entity.getDescription());
        dto.setTitle(entity.getTitle());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    protected Listening convertToEntity(ListeningDTO dto){
        Listening entity = new Listening();
        entity.setId(dto.getId());
        entity.setSerial(dto.getSerial());
        entity.setImage(dto.getImage());
        entity.setDescription(dto.getDescription());
        entity.setTitle(dto.getTitle());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}
