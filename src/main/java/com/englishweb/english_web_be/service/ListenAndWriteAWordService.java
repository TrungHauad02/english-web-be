package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ListenAndWriteAWordDTO;
import com.englishweb.english_web_be.model.ListenAndWriteAWord;
import com.englishweb.english_web_be.repository.ListenAndWriteAWordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListenAndWriteAWordService extends BaseService<ListenAndWriteAWord, ListenAndWriteAWordDTO, ListenAndWriteAWordRepository> {

    private final ListeningService listeningService;

    public ListenAndWriteAWordService(ListenAndWriteAWordRepository repository, ListeningService listeningService) {
        super(repository);
        this.listeningService = listeningService;
    }

    public List<ListenAndWriteAWordDTO> retrieveListenAndWriteAWordByListeningId(String listeningId) {
        List<ListenAndWriteAWord> entityList = repository.findAllByListening_Id(listeningId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected ListenAndWriteAWordDTO convertToDTO(ListenAndWriteAWord entity) {
        ListenAndWriteAWordDTO dto = new ListenAndWriteAWordDTO();
        dto.setId(entity.getId());
        dto.setAudioUrl(entity.getAudioUrl());
        dto.setSerial(entity.getSerial());
        dto.setCorrectAnswer(entity.getCorrectAnswer());
        dto.setSentence(entity.getSentence());
        dto.setMissingIndex(entity.getMissingIndex());
        dto.setStatus(entity.getStatus());
        dto.setListeningId(entity.getListening().getId());
        return dto;
    }

    @Override
    protected ListenAndWriteAWord convertToEntity(ListenAndWriteAWordDTO dto) {
        ListenAndWriteAWord entity = new ListenAndWriteAWord();
        entity.setId(dto.getId());
        entity.setAudioUrl(dto.getAudioUrl());
        entity.setSerial(dto.getSerial());
        entity.setCorrectAnswer(dto.getCorrectAnswer());
        entity.setSentence(dto.getSentence());
        entity.setMissingIndex(dto.getMissingIndex());
        entity.setStatus(dto.getStatus());
        entity.setListening(listeningService.convertToEntity(listeningService.findById(dto.getListeningId())));
        return entity;
    }
}
