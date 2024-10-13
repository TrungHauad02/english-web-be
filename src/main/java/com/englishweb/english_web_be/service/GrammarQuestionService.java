package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.GrammarQuestionDTO;
import com.englishweb.english_web_be.model.GrammarQuestion;
import com.englishweb.english_web_be.repository.GrammarQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrammarQuestionService {
    GrammarQuestionRepository repository;
    GrammarAnswerService grammarAnswerService;

    public GrammarQuestionService(GrammarQuestionRepository repository, GrammarAnswerService grammarAnswerService) {
        this.repository = repository;
        this.grammarAnswerService = grammarAnswerService;
    }

    public List<GrammarQuestionDTO> retrieveQuestionByGrammarId(String grammarId) {
        List<GrammarQuestion> entityList = repository.findAllByGrammar_Id(grammarId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private GrammarQuestionDTO convertToDTO(GrammarQuestion entity) {
        GrammarQuestionDTO dto = new GrammarQuestionDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setExplanation(entity.getExplanation());
        dto.setSerial(entity.getSerial());
        dto.setAnswers(grammarAnswerService.findAllByQuestionId(entity.getId()));
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
