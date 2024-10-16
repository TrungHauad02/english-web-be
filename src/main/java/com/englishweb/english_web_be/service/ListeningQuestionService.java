package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ListeningQuestionDTO;
import com.englishweb.english_web_be.model.ListeningQuestion;
import com.englishweb.english_web_be.repository.ListeningQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListeningQuestionService extends BaseService<ListeningQuestion, ListeningQuestionDTO, ListeningQuestionRepository> {
    private final ListenPracticeService listenPracticeService;
    ListeningAnswerService answerService;

    public ListeningQuestionService(ListeningQuestionRepository repository, ListeningAnswerService answerService, ListeningService listeningService, ListenPracticeService listenPracticeService) {
        super(repository);
        this.answerService = answerService;
        this.listenPracticeService = listenPracticeService;
    }

    public List<ListeningQuestionDTO> retrieveListeningQuestionsByListenPracticeId(String listenPracticeId) {
        List<ListeningQuestion> entityList = repository.findAllByListenPractice_Id(listenPracticeId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected ListeningQuestionDTO convertToDTO(ListeningQuestion entity) {
        ListeningQuestionDTO dto = new ListeningQuestionDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setExplanation(entity.getExplanation());
        dto.setSerial(entity.getSerial());
        dto.setStatus(entity.getStatus());
        dto.setAnswers(answerService.retrieveListeningAnswersByQuestionId(entity.getId()));
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
