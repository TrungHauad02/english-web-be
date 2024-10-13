package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ListenAndWriteAWordDTO;
import com.englishweb.english_web_be.model.ListenAndWriteAWord;
import com.englishweb.english_web_be.repository.ListenAndWriteAWordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListenAndWriteAWordService {
    ListenAndWriteAWordRepository repository;

    public ListenAndWriteAWordService(ListenAndWriteAWordRepository repository) {
        this.repository = repository;
    }

    public List<ListenAndWriteAWordDTO> retrieveListenAndWriteAWordByListeningId(String listeningId) {
        List<ListenAndWriteAWord> entityList = repository.findAllByListening_Id(listeningId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private ListenAndWriteAWordDTO convertToDTO(ListenAndWriteAWord entity) {
        ListenAndWriteAWordDTO dto = new ListenAndWriteAWordDTO();
        dto.setId(entity.getId());
        dto.setAudioUrl(entity.getAudioUrl());
        dto.setSerial(entity.getSerial());
        dto.setCorrectAnswer(entity.getCorrectAnswer());
        dto.setSentence(entity.getSentence());
        dto.setMissingIndex(entity.getMissingIndex());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
