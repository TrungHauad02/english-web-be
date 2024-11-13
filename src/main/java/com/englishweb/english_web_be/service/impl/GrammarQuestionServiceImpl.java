package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.GrammarAnswerDTO;
import com.englishweb.english_web_be.dto.GrammarQuestionDTO;
import com.englishweb.english_web_be.model.GrammarQuestion;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.GrammarQuestionRepository;
import com.englishweb.english_web_be.service.GrammarQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GrammarQuestionServiceImpl extends BaseServiceImpl<GrammarQuestion, GrammarQuestionDTO,
        GrammarQuestionRepository> implements GrammarQuestionService {
    private final GrammarServiceImpl grammarService;
    GrammarAnswerServiceImpl grammarAnswerService;

    public GrammarQuestionServiceImpl(GrammarQuestionRepository repository,
                                      GrammarAnswerServiceImpl grammarAnswerService,
                                      @Lazy GrammarServiceImpl grammarService) {
        super(repository);
        this.grammarAnswerService = grammarAnswerService;
        this.grammarService = grammarService;
    }

    public List<GrammarQuestionDTO> findAllByGrammarId(String grammarId) {
        grammarService.isExist(grammarId);
        List<GrammarQuestion> entityList = repository.findAllByGrammar_Id(grammarId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<GrammarQuestionDTO> findAllByGrammarIdAndStatus(String grammarId, StatusEnum status) {
        if(status == null)
            return findAllByGrammarId(grammarId);
        grammarService.isExist(grammarId);
        List<GrammarQuestion> entityList = repository.findAllByGrammar_IdAndStatus(grammarId, status);

        return entityList.stream()
                .map(this::convertToDTO)
                .peek(responseDTO -> {
                    List<GrammarAnswerDTO> filteredAnswers = responseDTO.getAnswers().stream()
                            .filter(answer -> answer.getStatus() == status)
                            .toList();
                    responseDTO.setAnswers(filteredAnswers);
                })
                .toList();
    }

    @Override
    public GrammarQuestionDTO create(GrammarQuestionDTO dto) {
        if (isSerialUnique(dto.getGrammarId(), dto.getSerial(), null)) {
            throw new DataIntegrityViolationException("Serial " + dto.getSerial() + " must be unique for Grammar ID: " + dto.getGrammarId());
        }
        return super.create(dto);
    }

    @Override
    public GrammarQuestionDTO update(GrammarQuestionDTO dto, String id) {
        if (isSerialUnique(dto.getGrammarId(), dto.getSerial(), id)) {
            throw new DataIntegrityViolationException("Serial " + dto.getSerial() + " must be unique for Grammar ID: " + dto.getGrammarId());
        }
        return super.update(dto, id);
    }

    private boolean isSerialUnique(String topicId, Integer serial, String excludeId) {
        List<GrammarQuestion> entityList = repository.findAllByGrammar_Id(topicId);
        return entityList.stream()
                .filter(entity -> !entity.getId().equals(excludeId))
                .anyMatch(entity -> entity.getSerial() == serial);
    }

    @Override
    public void delete(String id){
        List<GrammarAnswerDTO> answerDTOList = grammarAnswerService.findAllByQuestionId(id);
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
