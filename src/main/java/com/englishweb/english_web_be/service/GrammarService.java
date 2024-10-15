package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.GrammarDTO;
import com.englishweb.english_web_be.model.Grammar;
import com.englishweb.english_web_be.repository.GrammarRepository;
import org.springframework.stereotype.Service;

@Service
public class GrammarService extends BaseService<Grammar, GrammarDTO, GrammarRepository>{

    public GrammarService(GrammarRepository repository) {
        super(repository);
    }

    @Override
    protected Grammar convertToEntity(GrammarDTO grammarDTO) {
        Grammar entity = new Grammar();
        entity.setContent(grammarDTO.getContent());
        entity.setExample(grammarDTO.getExample());
        entity.setFile(grammarDTO.getFile());
        entity.setImage(grammarDTO.getImage());
        entity.setTitle(grammarDTO.getTitle());
        entity.setSerial(grammarDTO.getSerial());
        entity.setStatus(grammarDTO.getStatus());
        return entity;
    }

    @Override
    protected GrammarDTO convertToDTO(Grammar entity) {
        GrammarDTO dto = new GrammarDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setExample(entity.getExample());
        dto.setFile(entity.getFile());
        dto.setImage(entity.getImage());
        dto.setSerial(entity.getSerial());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
