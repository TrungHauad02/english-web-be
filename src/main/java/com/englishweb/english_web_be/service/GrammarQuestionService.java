package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.GrammarQuestionDTO;
import com.englishweb.english_web_be.model.GrammarQuestion;
import com.englishweb.english_web_be.repository.GrammarQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrammarQuestionService extends BaseService<GrammarQuestion, GrammarQuestionDTO, GrammarQuestionRepository>{
    private final GrammarService grammarService;
    GrammarAnswerService grammarAnswerService;

    public GrammarQuestionService(GrammarQuestionRepository repository, GrammarAnswerService grammarAnswerService, GrammarService grammarService) {
        super(repository);
        this.grammarAnswerService = grammarAnswerService;
        this.grammarService = grammarService;
    }

    public List<GrammarQuestionDTO> findAllByGrammarId(String grammarId) {
        List<GrammarQuestion> entityList = repository.findAllByGrammar_Id(grammarId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected GrammarQuestionDTO convertToDTO(GrammarQuestion entity) {
        GrammarQuestionDTO dto = new GrammarQuestionDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setExplanation(entity.getExplanation());
        dto.setSerial(entity.getSerial());
        dto.setAnswers(grammarAnswerService.findAllByQuestionId(entity.getId()));
        dto.setStatus(entity.getStatus());
        dto.setGrammarId(entity.getGrammar().getId());
        return dto;
    }

    @Override
    protected GrammarQuestion convertToEntity(GrammarQuestionDTO dto) {
        GrammarQuestion entity = new GrammarQuestion();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setExplanation(dto.getExplanation());
        entity.setSerial(dto.getSerial());
        entity.setStatus(dto.getStatus());
        entity.setGrammar(grammarService.convertToEntity(grammarService.findById(dto.getGrammarId())));
        return entity;
    }
}
