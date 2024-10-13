package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.GrammarDTO;
import com.englishweb.english_web_be.model.Grammar;
import com.englishweb.english_web_be.repository.GrammarRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class GrammarService {
    GrammarRepository repository;

    public GrammarService(GrammarRepository repository) {
        this.repository = repository;
    }

    public Page<GrammarDTO> retrieveGrammarsByPage(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Grammar> entityPages = repository.findAllGrammars(pageable);
        return entityPages.map(this::convertToDTO);
    }

    public Page<GrammarDTO> retrieveGrammarsByPage(int page, int size, Sort sort){
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Grammar> entityPages = repository.findAllGrammars(pageable);
        return entityPages.map(this::convertToDTO);
    }

    private GrammarDTO convertToDTO(Grammar entity) {
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
