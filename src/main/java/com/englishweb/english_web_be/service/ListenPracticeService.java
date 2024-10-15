package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ListenPracticeDTO;
import com.englishweb.english_web_be.model.ListenPractice;
import com.englishweb.english_web_be.repository.ListenPracticeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListenPracticeService extends BaseService<ListenPractice, ListenPracticeDTO, ListenPracticeRepository> {

    private final ListeningService listeningService;

    public ListenPracticeService(ListenPracticeRepository repository, ListeningService listeningService) {
        super(repository);
        this.listeningService = listeningService;
    }

    public List<ListenPracticeDTO> retrieveListenPracticeByListeningId(String listeningId) {
        List<ListenPractice> entityList = repository.findAllByListening_Id(listeningId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected ListenPracticeDTO convertToDTO(ListenPractice entity) {
        ListenPracticeDTO dto = new ListenPracticeDTO();
        dto.setId(entity.getId());
        dto.setAudioUrl(entity.getAudioUrl());
        dto.setStatus(entity.getStatus());
        dto.setListeningId(entity.getListening().getId());
        return dto;
    }

    @Override
    protected ListenPractice convertToEntity(ListenPracticeDTO dto) {
        ListenPractice entity = new ListenPractice();
        entity.setId(dto.getId());
        entity.setAudioUrl(dto.getAudioUrl());
        entity.setStatus(dto.getStatus());
        entity.setListening(listeningService.convertToEntity(listeningService.findById(dto.getListeningId())));
        return entity;
    }
}
