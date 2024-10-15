package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.GrammarAnswerDTO;
import com.englishweb.english_web_be.model.GrammarAnswer;
import com.englishweb.english_web_be.repository.GrammarAnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrammarAnswerService extends BaseService<GrammarAnswer, GrammarAnswerDTO, GrammarAnswerRepository> {

    GrammarQuestionService grammarQuestionService;

    public GrammarAnswerService(GrammarAnswerRepository repository, GrammarQuestionService grammarQuestionService) {
        super(repository);
        this.grammarQuestionService = grammarQuestionService;
    }

    public List<GrammarAnswerDTO> findAllByQuestionId(String questionId) {
        List<GrammarAnswer> entityList = repository.findAllByQuestion_Id(questionId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected GrammarAnswerDTO convertToDTO(GrammarAnswer entity) {
        GrammarAnswerDTO dto = new GrammarAnswerDTO();
        dto.setId(entity.getId());
        dto.setCorrect(entity.isCorrect());
        dto.setContent(entity.getContent());
        dto.setStatus(entity.getStatus());
        dto.setQuestionId(entity.getQuestion().getId());
        return dto;
    }

    @Override
    protected GrammarAnswer convertToEntity(GrammarAnswerDTO dto) {
        GrammarAnswer entity = new GrammarAnswer();
        entity.setId(dto.getId());
        entity.setCorrect(dto.isCorrect());
        entity.setContent(dto.getContent());
        entity.setStatus(dto.getStatus());
        entity.setQuestion(grammarQuestionService.convertToEntity(grammarQuestionService.findById(dto.getId())));
        return entity;
    }
}
