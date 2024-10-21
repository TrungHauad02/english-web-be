package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ListenPracticeDTO;
import com.englishweb.english_web_be.dto.ListeningQuestionDTO;
import com.englishweb.english_web_be.model.ListenPractice;
import com.englishweb.english_web_be.repository.ListenPracticeRepository;
import com.englishweb.english_web_be.util.ValidationUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListenPracticeService extends BaseService<ListenPractice, ListenPracticeDTO, ListenPracticeRepository> {

    private final ListeningService listeningService;
    private final ListeningQuestionService listeningQuestionService;

    public ListenPracticeService(ListenPracticeRepository repository, @Lazy ListeningService listeningService, ListeningQuestionService listeningQuestionService) {
        super(repository);
        this.listeningService = listeningService;
        this.listeningQuestionService = listeningQuestionService;
    }

    public ListenPracticeDTO findByListeningId(String listeningId) {
        listeningService.isExist(listeningId);
        ListenPractice entity = repository.findByListening_Id(listeningId);
        return convertToDTO(entity);
    }

    @Override
    public void delete(String id){
        List<ListeningQuestionDTO> questionDTOList = listeningQuestionService.findByListenPracticeId(id);
        super.delete(id);
        for(ListeningQuestionDTO questionDTO : questionDTOList){
            listeningQuestionService.delete(questionDTO.getId());
        }
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
