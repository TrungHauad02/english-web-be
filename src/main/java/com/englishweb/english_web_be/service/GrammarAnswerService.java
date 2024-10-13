package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.GrammarAnswerDTO;
import com.englishweb.english_web_be.model.GrammarAnswer;
import com.englishweb.english_web_be.repository.GrammarAnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrammarAnswerService {
    GrammarAnswerRepository repository;

    public GrammarAnswerService(GrammarAnswerRepository repository) {
        this.repository = repository;
    }

    public List<GrammarAnswerDTO> findAllByQuestionId(String questionId) {
        List<GrammarAnswer> entityList = repository.findAllByQuestion_Id(questionId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private GrammarAnswerDTO convertToDTO(GrammarAnswer entity) {
        GrammarAnswerDTO dto = new GrammarAnswerDTO();
        dto.setId(entity.getId());
        dto.setCorrect(entity.isCorrect());
        dto.setContent(entity.getContent());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
