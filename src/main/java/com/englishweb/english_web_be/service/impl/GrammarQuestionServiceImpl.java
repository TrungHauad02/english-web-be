package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.GrammarAnswerDTO;
import com.englishweb.english_web_be.dto.GrammarQuestionDTO;
import com.englishweb.english_web_be.dto.request.GrammarQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.GrammarQuestionResponseDTO;
import com.englishweb.english_web_be.mapper.GrammarQuestionMapper;
import com.englishweb.english_web_be.model.GrammarQuestion;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.GrammarQuestionRepository;
import com.englishweb.english_web_be.service.GrammarQuestionService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrammarQuestionServiceImpl extends BaseServiceImpl<GrammarQuestion, GrammarQuestionDTO,
        GrammarQuestionRequestDTO, GrammarQuestionResponseDTO, GrammarQuestionMapper,
        GrammarQuestionRepository> implements GrammarQuestionService {
    private final GrammarServiceImpl grammarService;
    GrammarAnswerServiceImpl grammarAnswerService;

    public GrammarQuestionServiceImpl(GrammarQuestionRepository repository, GrammarAnswerServiceImpl grammarAnswerService, @Lazy GrammarServiceImpl grammarService, GrammarQuestionMapper mapper) {
        super(repository, mapper);
        this.grammarAnswerService = grammarAnswerService;
        this.grammarService = grammarService;
    }

    public List<GrammarQuestionResponseDTO> findAllByGrammarId(String grammarId) {
        grammarService.isExist(grammarId);
        List<GrammarQuestion> entityList = repository.findAllByGrammar_Id(grammarId);
        return entityList.stream()
                .map(this::convertToDTO)
                .map(mapper::mapToResponseDTO)
                .toList();
    }

    public List<GrammarQuestionDTO> findAllDTOByGrammarId(String grammarId) {
        grammarService.isExist(grammarId);
        List<GrammarQuestion> entityList = repository.findAllByGrammar_Id(grammarId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<GrammarQuestionResponseDTO> findAllByGrammarIdAndStatus(String grammarId, StatusEnum status) {
        if(status == null)
            return findAllByGrammarId(grammarId);
        grammarService.isExist(grammarId);
        List<GrammarQuestion> entityList = repository.findAllByGrammar_IdAndStatus(grammarId, status);
        return entityList.stream()
                .map(this::convertToDTO)
                .map(mapper::mapToResponseDTO)
                .toList();
    }

    @Override
    public void delete(String id){
        List<GrammarAnswerDTO> answerDTOList = grammarAnswerService.findAllDTOByQuestionId(id);
        for(GrammarAnswerDTO answerDTO : answerDTOList){
            grammarAnswerService.delete(answerDTO.getId());
        }
        super.delete(id);
    }

    @Override
    protected GrammarQuestionDTO convertToDTO(GrammarQuestion entity) {
        GrammarQuestionDTO dto = new GrammarQuestionDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setExplanation(entity.getExplanation());
        dto.setSerial(entity.getSerial());
        dto.setAnswers(grammarAnswerService.findAllDTOByQuestionId(entity.getId()));
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
        entity.setGrammar(grammarService.convertToEntity(grammarService.findDTOById(dto.getGrammarId())));
        return entity;
    }
}
