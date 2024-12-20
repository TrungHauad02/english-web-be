package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.ListeningAnswerDTO;
import com.englishweb.english_web_be.model.ListeningAnswer;
import com.englishweb.english_web_be.repository.ListeningAnswerRepository;
import com.englishweb.english_web_be.service.ListeningAnswerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListeningAnswerServiceImpl extends BaseServiceImpl<ListeningAnswer, ListeningAnswerDTO,
        ListeningAnswerRepository>
        implements ListeningAnswerService {

    private final ListeningQuestionServiceImpl listeningQuestionService;

    public ListeningAnswerServiceImpl(ListeningAnswerRepository repository,
                                      @Lazy ListeningQuestionServiceImpl listeningQuestionService) {
        super(repository);
        this.listeningQuestionService = listeningQuestionService;
    }

    @Override
    public List<ListeningAnswerDTO> findByQuestionId(String questionId) {
        listeningQuestionService.isExist(questionId);
        List<ListeningAnswer> entityList = repository.findAllByQuestion_Id(questionId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected ListeningAnswerDTO convertToDTO(ListeningAnswer entity) {
        ListeningAnswerDTO dto = new ListeningAnswerDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setCorrect(entity.isCorrect());
        dto.setStatus(entity.getStatus());
        dto.setQuestionId(entity.getQuestion().getId());
        return dto;
    }

    @Override
    protected ListeningAnswer convertToEntity(ListeningAnswerDTO dto) {
        ListeningAnswer entity = new ListeningAnswer();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setCorrect(dto.isCorrect());
        entity.setStatus(dto.getStatus());
        entity.setQuestion(listeningQuestionService.convertToEntity(
                            listeningQuestionService.findById(dto.getQuestionId())));
        return entity;
    }
}
