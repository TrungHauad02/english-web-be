package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ReadingDTO;
import com.englishweb.english_web_be.model.Reading;
import com.englishweb.english_web_be.repository.ReadingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public ReadingDTO retrieveReadingById(String id){
        return convertToDTO(repository.findById(id).get());
    }

    public ReadingDTO addNewReading(ReadingDTO dto){
        Reading reading = new Reading();
        reading.setTitle(dto.getTitle());
        reading.setDescription(dto.getDescription());
        reading.setContent(dto.getContent());
        reading.setSerial(dto.getSerial());
        reading.setImage(reading.getImage());
        reading.setStatus(dto.getStatus());
        repository.save(reading);
        return convertToDTO(reading);
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
