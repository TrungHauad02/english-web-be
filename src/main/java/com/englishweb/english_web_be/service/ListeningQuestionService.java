package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ListeningAnswerDTO;
import com.englishweb.english_web_be.dto.ListeningQuestionDTO;
import com.englishweb.english_web_be.model.ListeningQuestion;
import com.englishweb.english_web_be.repository.ListeningQuestionRepository;
import com.englishweb.english_web_be.util.ValidationUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListeningQuestionService extends BaseService<ListeningQuestion, ListeningQuestionDTO, ListeningQuestionRepository> {
    private final ListeningService listeningService;
    ListeningAnswerService answerService;

    public ListeningQuestionService(ListeningQuestionRepository repository, ListeningAnswerService answerService, @Lazy ListeningService listeningService) {
        super(repository);
        this.answerService = answerService;
        this.listeningService = listeningService;
    }

    public List<ListeningQuestionDTO> findByListeningId(String listeningId) {
        listeningService.isExist(listeningId);
        List<ListeningQuestion> entityList = repository.findAllByListening_Id(listeningId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public void delete(String id){
        isExist(id);
        List<ListeningAnswerDTO> answerDTOList = answerService.findListeningAnswersByQuestionId(id);
        for(ListeningAnswerDTO answerDTO : answerDTOList){
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
        dto.setAnswers(answerService.findListeningAnswersByQuestionId(entity.getId()));
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
