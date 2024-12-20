package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.GrammarAnswerDTO;
import com.englishweb.english_web_be.model.GrammarAnswer;
import com.englishweb.english_web_be.repository.GrammarAnswerRepository;
import com.englishweb.english_web_be.service.GrammarAnswerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrammarAnswerServiceImpl extends BaseServiceImpl<GrammarAnswer, GrammarAnswerDTO,
        GrammarAnswerRepository> implements GrammarAnswerService {

    GrammarQuestionServiceImpl grammarQuestionService;

    public GrammarAnswerServiceImpl(GrammarAnswerRepository repository, @Lazy GrammarQuestionServiceImpl grammarQuestionService) {
        super(repository);
        this.grammarQuestionService = grammarQuestionService;
    }

    public List<GrammarAnswerDTO> findAllByQuestionId(String questionId) {
        grammarQuestionService.isExist(questionId);
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
        entity.setQuestion(grammarQuestionService.convertToEntity(grammarQuestionService.findById(dto.getQuestionId())));
        return entity;
    }
}
