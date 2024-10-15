package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.GrammarDTO;
import com.englishweb.english_web_be.model.Grammar;
import com.englishweb.english_web_be.repository.GrammarRepository;
import com.englishweb.english_web_be.util.ValidationUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class GrammarService {
    GrammarRepository repository;

    public GrammarService(GrammarRepository repository) {
        this.repository = repository;
    }

    public Page<GrammarDTO> retrieveGrammarsByPage(int page, int size){
        ValidationUtils.getInstance().validatePageRequestParam(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.by("serial"));
        Page<Grammar> entityPage = repository.findAllGrammars(pageable);
        return entityPage.map(this::convertToDTO);
    }

    public GrammarDTO retrieveGrammarById(String id){
        ValidationUtils.getInstance().validateExistId(repository, id);
        Grammar entity = repository.findById(id).get();
        return convertToDTO(entity);
    }

    public GrammarDTO createGrammar(GrammarDTO dto){
        Grammar entity = convertToEntity(dto);
        entity = repository.save(entity);
        return convertToDTO(entity);
    }

    public GrammarDTO updateGrammar(GrammarDTO dto){
        ValidationUtils.getInstance().validateExistId(repository, dto.getId());
        Grammar entity = convertToEntity(dto);
        entity.setId(dto.getId());
        entity = repository.save(entity);
        return convertToDTO(entity);
    }

    public void deleteGrammar(String id){
        ValidationUtils.getInstance().validateExistId(repository, id);
        repository.deleteById(id);
    }

    private Grammar convertToEntity(GrammarDTO grammarDTO) {
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
