package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.SpeakingDTO;
import com.englishweb.english_web_be.model.Speaking;
import com.englishweb.english_web_be.repository.SpeakingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SpeakingService {
    SpeakingRepository repository;

    public SpeakingService(SpeakingRepository repository) {
        this.repository = repository;
    }

    public Page<SpeakingDTO> retrieveSpeakingsByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Speaking> speakings = repository.findAllSpeakings(pageable);
        return speakings.map(this::convertToDTO);
    }

    public Page<SpeakingDTO> retrieveSpeakingsByPage(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Speaking> speakings = repository.findAllSpeakings(pageable);
        return speakings.map(this::convertToDTO);
    }

    private SpeakingDTO convertToDTO(Speaking entity) {
        SpeakingDTO dto = new SpeakingDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setImage(entity.getImage());
        dto.setSerial(entity.getSerial());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
