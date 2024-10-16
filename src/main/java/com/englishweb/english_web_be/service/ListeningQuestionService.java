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
    private final ListenPracticeService listenPracticeService;
    ListeningAnswerService answerService;

    public ListeningQuestionService(ListeningQuestionRepository repository, ListeningAnswerService answerService, @Lazy ListenPracticeService listenPracticeService) {
        super(repository);
        this.answerService = answerService;
        this.listenPracticeService = listenPracticeService;
    }

    public List<ListeningQuestionDTO> findByListenPracticeId(String listenPracticeId) {
        ValidationUtils.getInstance().validateExistId(listenPracticeService.repository, listenPracticeId);
        List<ListeningQuestion> entityList = repository.findAllByListenPractice_Id(listenPracticeId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public void delete(String id){
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
        dto.setListeningPracticeId(entity.getListenPractice().getId());
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
        entity.setListenPractice(listenPracticeService.convertToEntity(listenPracticeService.findById(dto.getListeningPracticeId())));
        return entity;
    }
}
