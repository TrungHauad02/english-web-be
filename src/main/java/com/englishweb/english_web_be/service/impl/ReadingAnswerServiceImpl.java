package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.ReadingAnswerDTO;
import com.englishweb.english_web_be.model.ReadingAnswer;
import com.englishweb.english_web_be.repository.ReadingAnswerRepository;
import com.englishweb.english_web_be.service.ReadingAnswerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingAnswerServiceImpl extends BaseServiceImpl<ReadingAnswer, ReadingAnswerDTO,
        ReadingAnswerRepository> implements ReadingAnswerService {

    private final ReadingQuestionServiceImpl readingQuestionService;

    public ReadingAnswerServiceImpl(ReadingAnswerRepository repository,
                                    @Lazy ReadingQuestionServiceImpl readingQuestionService) {
        super(repository);
        this.readingQuestionService = readingQuestionService;
    }

    @Override
    public List<ReadingAnswerDTO> findAllByQuestionId(String questionId) {
        readingQuestionService.isExist(questionId);
        List<ReadingAnswer> entityList = repository.findAllByQuestion_Id(questionId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected ReadingAnswerDTO convertToDTO(ReadingAnswer entity) {
        ReadingAnswerDTO dto = new ReadingAnswerDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setCorrect(entity.isCorrect());
        dto.setStatus(entity.getStatus());
        dto.setQuestionId(entity.getQuestion().getId());
        return dto;
    }

    @Override
    protected ReadingAnswer convertToEntity(ReadingAnswerDTO dto) {
        ReadingAnswer entity = new ReadingAnswer();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setCorrect(dto.isCorrect());
        entity.setStatus(dto.getStatus());
        entity.setQuestion(readingQuestionService.convertToEntity(readingQuestionService.findById(dto.getQuestionId())));
        return entity;
    }
}