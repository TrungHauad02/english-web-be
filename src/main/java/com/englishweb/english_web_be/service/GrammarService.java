package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.GrammarDTO;
import com.englishweb.english_web_be.dto.GrammarQuestionDTO;
import com.englishweb.english_web_be.model.Grammar;
import com.englishweb.english_web_be.repository.GrammarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrammarService extends BaseService<Grammar, GrammarDTO, GrammarRepository>{

    private final GrammarQuestionService grammarQuestionService;

    public GrammarService(GrammarRepository repository, GrammarQuestionService grammarQuestionService) {
        super(repository);
        this.grammarQuestionService = grammarQuestionService;
    }

    @Override
    public void delete(String id){
        List<GrammarQuestionDTO> questionDTOList = grammarQuestionService.findAllByGrammarId(id);
        for(GrammarQuestionDTO questionDTO : questionDTOList){
            grammarQuestionService.delete(questionDTO.getId());
        }
        super.delete(id);
    }

    @Override
    protected GrammarDTO convertToDTO(Grammar entity) {
        GrammarDTO dto = new GrammarDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setExample(entity.getExample());
        dto.setFile(entity.getFile());
        dto.setImage(entity.getImage());
        dto.setSerial(entity.getSerial());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    protected Grammar convertToEntity(GrammarDTO dto) {
        Grammar entity = new Grammar();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setExample(dto.getExample());
        entity.setFile(dto.getFile());
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setSerial(dto.getSerial());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}
