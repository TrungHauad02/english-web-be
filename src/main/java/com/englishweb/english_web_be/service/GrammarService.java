package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.GrammarDTO;
import com.englishweb.english_web_be.model.Grammar;
import com.englishweb.english_web_be.model.Topic;
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
        Page<Grammar> entityPage = repository.findAllGrammars(pageable);
        return entityPage.map(this::convertToDTO);
    }

    public Page<GrammarDTO> retrieveGrammarsByPage(int page, int size, Sort sort){
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Grammar> entityPage = repository.findAllGrammars(pageable);
        return entityPage.map(this::convertToDTO);
    }

    public GrammarDTO retrieveGrammarById(String id){
        if(repository.findById(id).isEmpty())
            return null;
        Grammar entity = repository.findById(id).get();
        return convertToDTO(entity);
    }

    public GrammarDTO createGrammar(GrammarDTO grammarDTO){
        Grammar entity = convertToEntity(grammarDTO);
        entity = repository.save(entity);
        return convertToDTO(entity);
    }

    public GrammarDTO updateGrammar(GrammarDTO dto){
        if(repository.findById(dto.getId()).isEmpty())
            return null;
        Grammar entity = convertToEntity(dto);
        entity.setId(dto.getId());
        entity = repository.save(entity);
        return convertToDTO(entity);
    }

    public boolean deleteGrammar(String id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
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
