package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ListenPracticeDTO;
import com.englishweb.english_web_be.model.ListenPractice;
import com.englishweb.english_web_be.repository.ListenPracticeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListenPracticeService {
    ListenPracticeRepository repository;

    public ListenPracticeService(ListenPracticeRepository repository) {
        this.repository = repository;
    }

    public List<ListenPracticeDTO> retrieveListenPracticeByListeningId(String listeningId) {
        List<ListenPractice> entityList = repository.findAllByListening_Id(listeningId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private ListenPracticeDTO convertToDTO(ListenPractice entity) {
        ListenPracticeDTO dto = new ListenPracticeDTO();
        dto.setId(entity.getId());
        dto.setAudioUrl(entity.getAudioUrl());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
