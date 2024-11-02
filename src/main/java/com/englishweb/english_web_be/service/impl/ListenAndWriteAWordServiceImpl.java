package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.ListenAndWriteAWordDTO;
import com.englishweb.english_web_be.dto.request.ListenAndWriteAWordRequestDTO;
import com.englishweb.english_web_be.dto.response.ListenAndWriteAWordResponseDTO;
import com.englishweb.english_web_be.mapper.ListenAndWriteAWordMapper;
import com.englishweb.english_web_be.model.ListenAndWriteAWord;
import com.englishweb.english_web_be.repository.ListenAndWriteAWordRepository;
import com.englishweb.english_web_be.service.ListenAndWriteAWordService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListenAndWriteAWordServiceImpl extends BaseServiceImpl<ListenAndWriteAWord, ListenAndWriteAWordDTO,
        ListenAndWriteAWordRequestDTO, ListenAndWriteAWordResponseDTO, ListenAndWriteAWordMapper, ListenAndWriteAWordRepository>
        implements ListenAndWriteAWordService {

    private final ListeningServiceImpl listeningService;

    public ListenAndWriteAWordServiceImpl(ListenAndWriteAWordRepository repository, @Lazy ListeningServiceImpl listeningService, ListenAndWriteAWordMapper mapper) {
        super(repository, mapper);
        this.listeningService = listeningService;
    }

    @Override
    public List<ListenAndWriteAWordResponseDTO> findByListeningId(String listeningId) {
        listeningService.isExist(listeningId);
        List<ListenAndWriteAWord> entityList = repository.findAllByListening_Id(listeningId);
        return entityList.stream()
                .map(this::convertToDTO)
                .map(mapper::mapToResponseDTO)
                .toList();
    }

    @Override
    public List<ListenAndWriteAWordDTO> findDTOByListeningId(String listeningId) {
        listeningService.isExist(listeningId);
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
        entity.setListening(listeningService.convertToEntity(listeningService.findDTOById(dto.getListeningId())));
        return entity;
    }
}
