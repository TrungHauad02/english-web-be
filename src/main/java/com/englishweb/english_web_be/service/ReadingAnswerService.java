package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ReadingAnswerDTO;
import com.englishweb.english_web_be.model.ReadingAnswer;
import com.englishweb.english_web_be.repository.ReadingAnswerRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingAnswerService extends BaseService<ReadingAnswer, ReadingAnswerDTO, ReadingAnswerRepository> {
    ReadingQuestionService readingQuestionService;

    public ReadingAnswerService(ReadingAnswerRepository repository,@Lazy ReadingQuestionService readingQuestionService) {
        super(repository);
        this.readingQuestionService = readingQuestionService;
    }

    public List<ReadingAnswerDTO> findAllByQuestionId(String id) {
        readingQuestionService.isExist(id);
        return repository.findAllByQuestion_id(id)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public ReadingAnswerDTO convertToDTO(ReadingAnswer entity) {
        ReadingAnswerDTO dto = new ReadingAnswerDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setCorrect(entity.isCorrect());
        dto.setStatus(entity.getStatus());
        dto.setQuestionId(entity.getQuestion().getId());
        return dto;
    }

    @Override
    public ReadingAnswer convertToEntity(ReadingAnswerDTO dto) {
        ReadingAnswer entity = new ReadingAnswer();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setCorrect(dto.isCorrect());
        entity.setStatus(dto.getStatus());
        entity.setQuestion(readingQuestionService.convertToEntity(readingQuestionService.findById(dto.getQuestionId())));
        return entity;
    }
}
