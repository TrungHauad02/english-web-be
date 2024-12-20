package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.ListeningAnswerDTO;
import com.englishweb.english_web_be.dto.ListeningQuestionDTO;
import com.englishweb.english_web_be.model.ListeningQuestion;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.ListeningQuestionRepository;
import com.englishweb.english_web_be.service.ListeningQuestionService;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListeningQuestionServiceImpl extends BaseServiceImpl<ListeningQuestion, ListeningQuestionDTO,
        ListeningQuestionRepository>
        implements ListeningQuestionService {

    private final ListeningServiceImpl listeningService;
    private final ListeningAnswerServiceImpl answerService;

    public ListeningQuestionServiceImpl(ListeningQuestionRepository repository,
                                        ListeningAnswerServiceImpl answerService,
                                        @Lazy ListeningServiceImpl listeningService) {
        super(repository);
        this.answerService = answerService;
        this.listeningService = listeningService;
    }

    @Override
    public List<ListeningQuestionDTO> findByListeningId(String listeningId) {
        listeningService.isExist(listeningId);
        List<ListeningQuestion> entityList = repository.findAllByListening_Id(listeningId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<ListeningQuestionDTO> findByListeningIdAndStatus(String listeningId, StatusEnum status) {
        if(status == null)
            return findByListeningId(listeningId);
        listeningService.isExist(listeningId);
        List<ListeningQuestion> entityList = repository.findAllByListening_IdAndStatus(listeningId, status);
        return entityList.stream()
                .map(this::convertToDTO)
                .peek(responseDTO -> {
                    List<ListeningAnswerDTO> filteredAnswers = responseDTO.getAnswers().stream()
                            .filter(answer -> answer.getStatus() == status)
                            .toList();
                    responseDTO.setAnswers(filteredAnswers);
                })
                .toList();
    }

    @Override
    public ListeningQuestionDTO create(ListeningQuestionDTO dto) {
        if (isSerialUnique(dto.getListeningId(), dto.getSerial(), null)) {
            throw new DataIntegrityViolationException("Serial " + dto.getSerial() + " must be unique for Listening ID: " + dto.getListeningId());
        }
        return super.create(dto);
    }

    @Override
    public ListeningQuestionDTO update(ListeningQuestionDTO dto, String id) {
        if (isSerialUnique(dto.getListeningId(), dto.getSerial(), id)) {
            throw new DataIntegrityViolationException("Serial " + dto.getSerial() + " must be unique for Listening ID: " + dto.getListeningId());
        }
        return super.update(dto, id);
    }

    private boolean isSerialUnique(String listeningId, Integer serial, String excludeId) {
        List<ListeningQuestion> entityList = repository.findAllByListening_Id(listeningId);
        return entityList.stream()
                .filter(entity -> !entity.getId().equals(excludeId))
                .anyMatch(entity -> entity.getSerial() == serial);
    }

    @Override
    public void delete(String id) {
        isExist(id);
        List<ListeningAnswerDTO> answerDTOList = answerService.findByQuestionId(id);
        for (ListeningAnswerDTO answerDTO : answerDTOList) {
            answerService.delete(answerDTO.getId());
        }
        super.delete(id);
    }

    @Override
    protected ListeningQuestionDTO convertToDTO(ListeningQuestion entity) {
        ListeningQuestionDTO dto = new ListeningQuestionDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setExplanation(entity.getExplanation());
        dto.setSerial(entity.getSerial());
        dto.setStatus(entity.getStatus());
        dto.setAnswers(answerService.findByQuestionId(entity.getId()));
        dto.setListeningId(entity.getListening().getId());
        return dto;
    }

    @Override
    protected ListeningQuestion convertToEntity(ListeningQuestionDTO dto) {
        ListeningQuestion entity = new ListeningQuestion();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setExplanation(dto.getExplanation());
        entity.setSerial(dto.getSerial());
        entity.setStatus(dto.getStatus());
        entity.setListening(listeningService.convertToEntity(listeningService.findById(dto.getListeningId())));
        return entity;
    }
}
