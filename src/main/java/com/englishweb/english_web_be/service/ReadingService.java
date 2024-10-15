package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ReadingDTO;
import com.englishweb.english_web_be.model.Reading;
import com.englishweb.english_web_be.repository.ReadingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ReadingService {
    ReadingRepository repository;

    public ReadingService(ReadingRepository repository) {
        this.repository = repository;
    }

    public Page<ReadingDTO> retrieveReadingsByPage(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Reading> entityPages = repository.findAllReadings(pageable);
        return entityPages.map(this::convertToDTO);
    }

    public Page<ReadingDTO> retrieveReadingsByPage(int page, int size, Sort sort){
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Reading> entityPages = repository.findAllReadings(pageable);
        return entityPages.map(this::convertToDTO);
    }

    public ReadingDTO retrieveReadingById(String id){
        return convertToDTO(repository.findById(id).get());
    }

    public ReadingDTO createReading(ReadingDTO dto){
        Reading reading = convertToEntity(dto);
        repository.save(reading);
        return convertToDTO(reading);
    }

    public ReadingDTO updateReading(ReadingDTO dto){
        if(repository.findById(dto.getId()).isEmpty()){
            return null;
        }
        Reading entity = repository.findById(dto.getId()).get();
        entity.setId(dto.getId());
        repository.save(entity);
        return convertToDTO(entity);
    }

    public boolean deleteReading(String id){
        if(repository.findById(id).isEmpty()){
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    private Reading convertToEntity(ReadingDTO dto){
        Reading entity = new Reading();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setSerial(dto.getSerial());
        entity.setImage(dto.getImage());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    private ReadingDTO convertToDTO(Reading entity) {
        ReadingDTO dto = new ReadingDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setContent(entity.getContent());
        dto.setSerial(entity.getSerial());
        dto.setImage(entity.getImage());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
