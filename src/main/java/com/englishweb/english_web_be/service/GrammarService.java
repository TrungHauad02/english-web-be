package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.GrammarDTO;
import com.englishweb.english_web_be.exception.InvalidArgumentException;
import com.englishweb.english_web_be.exception.ResourceNotFoundException;
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
        validateRequestParam(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.by("serial"));
        Page<Grammar> entityPage = repository.findAllGrammars(pageable);
        return entityPage.map(this::convertToDTO);
    }

    public GrammarDTO retrieveGrammarById(String id){
        validateExistId(id);
        Grammar entity = repository.findById(id).get();
        return convertToDTO(entity);
    }

    public GrammarDTO createGrammar(GrammarDTO dto){
        validateData(dto);
        Grammar entity = convertToEntity(dto);
        entity = repository.save(entity);
        return convertToDTO(entity);
    }

    public GrammarDTO updateGrammar(GrammarDTO dto){
        validateExistId(dto.getId());
        validateData(dto);
        Grammar entity = convertToEntity(dto);
        entity.setId(dto.getId());
        entity = repository.save(entity);
        return convertToDTO(entity);
    }

    public void deleteGrammar(String id){
        validateExistId(id);
        repository.deleteById(id);
    }

    private void validateRequestParam(int page, int size) {
        if(size <= 0) {
            throw new InvalidArgumentException("Size must be greater than 0");
        }
        if(page < 0) {
            throw new InvalidArgumentException("Page must be greater or equal than 0");
        }
    }

    private void validateExistId(String id){
        if(repository.findById(id).isEmpty())
            throw new ResourceNotFoundException("Cannot find Grammar with id " + id);
    }

    private void validateData(GrammarDTO dto){
        if(dto.getContent() == null || dto.getContent().trim().isEmpty())
            throw new InvalidArgumentException("Content cannot be empty");
        if(dto.getTitle() == null || dto.getTitle().trim().isEmpty())
            throw new InvalidArgumentException("Title cannot be empty");
        if(dto.getExample() == null || dto.getExample().trim().isEmpty())
            throw new InvalidArgumentException("Example cannot be empty");
        if(dto.getImage() == null || dto.getImage().trim().isEmpty())
            throw new InvalidArgumentException("Image cannot be empty");
        if(dto.getSerial() <= 0)
            throw new InvalidArgumentException("Serial number must be greater than 0");
        if(dto.getFile() == null || dto.getFile().trim().isEmpty())
            throw new InvalidArgumentException("File cannot be empty");
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
