package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.ListenAndWriteAWordDTO;
import com.englishweb.english_web_be.model.ListenAndWriteAWord;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.ListenAndWriteAWordRepository;
import com.englishweb.english_web_be.service.ListenAndWriteAWordService;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListenAndWriteAWordServiceImpl extends BaseServiceImpl<ListenAndWriteAWord, ListenAndWriteAWordDTO,
        ListenAndWriteAWordRepository>
        implements ListenAndWriteAWordService {

    private final ListeningServiceImpl listeningService;

    public ListenAndWriteAWordServiceImpl(ListenAndWriteAWordRepository repository,
                                          @Lazy ListeningServiceImpl listeningService) {
        super(repository);
        this.listeningService = listeningService;
    }

    @Override
    public List<ListenAndWriteAWordDTO> findByListeningId(String listeningId) {
        listeningService.isExist(listeningId);
        List<ListenAndWriteAWord> entityList = repository.findAllByListening_Id(listeningId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<ListenAndWriteAWordDTO> findByListeningIdAndStatus(String listeningId, StatusEnum status) {
        if(status == null)
            return findByListeningId(listeningId);
        listeningService.isExist(listeningId);
        List<ListenAndWriteAWord> entityList = repository.findAllByListening_IdAndStatus(listeningId, status);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public ListenAndWriteAWordDTO create(ListenAndWriteAWordDTO dto) {
        if (isSerialUnique(dto.getListeningId(), dto.getSerial(), null)) {
            throw new DataIntegrityViolationException("Serial " + dto.getSerial() + " must be unique for Listening ID: " + dto.getListeningId());
        }
        return super.create(dto);
    }

    @Override
    public ListenAndWriteAWordDTO update(ListenAndWriteAWordDTO dto, String id) {
        if (isSerialUnique(dto.getListeningId(), dto.getSerial(), id)) {
            throw new DataIntegrityViolationException("Serial " + dto.getSerial() + " must be unique for Listening ID: " + dto.getListeningId());
        }
        return super.update(dto, id);
    }

    private boolean isSerialUnique(String listenAndWriteId, Integer serial, String excludeId) {
        List<ListenAndWriteAWord> entityList = repository.findAllByListening_Id(listenAndWriteId);
        return entityList.stream()
                .filter(entity -> !entity.getId().equals(excludeId))
                .anyMatch(entity -> entity.getSerial() == serial);
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
