package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.WritingDTO;
import com.englishweb.english_web_be.model.Writing;
import com.englishweb.english_web_be.repository.WritingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WritingService {
    WritingRepository repository;

    public WritingService(WritingRepository repository) {
        this.repository = repository;
    }

    Page<WritingDTO> retrieveWritingsByPage(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Writing> writingPage = repository.findAllWritings(pageable);
        return writingPage.map(this::convertToDTO);
    }

    private WritingDTO convertToDTO(Writing entity) {
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
}
