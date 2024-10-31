package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ReadingQuestionDTO;
import com.englishweb.english_web_be.model.ReadingQuestion;
import com.englishweb.english_web_be.repository.ReadingQuestionRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingQuestionService extends BaseService<ReadingQuestion, ReadingQuestionDTO, ReadingQuestionRepository> {
    ReadingService readingService;
    ReadingAnswerService readingAnswerService;

    public ReadingQuestionService(ReadingQuestionRepository repository, @Lazy ReadingService readingService, ReadingAnswerService readingAnswerService) {
        super(repository);
        this.readingService = readingService;
        this.readingAnswerService = readingAnswerService;
    }

    public List<ReadingQuestionDTO> findAllByReadingId(String readingId) {
        readingService.isExist(readingId);
        return repository.findAllByReading_Id(readingId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public ReadingQuestionDTO convertToDTO(ReadingQuestion entity) {
        ReadingQuestionDTO dto = new ReadingQuestionDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setSerial(entity.getSerial());
        dto.setExplanation(entity.getExplanation());
        dto.setStatus(entity.getStatus());
        dto.setReadingId(entity.getReading().getId());
        dto.setAnswers(readingAnswerService.findAllByQuestionId(entity.getId()));
        return null;
    }

    @Override
    public ReadingQuestion convertToEntity(ReadingQuestionDTO dto) {
        ReadingQuestion entity = new ReadingQuestion();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setSerial(dto.getSerial());
        entity.setExplanation(dto.getExplanation());
        entity.setStatus(dto.getStatus());
        entity.setReading(readingService.convertToEntity(readingService.findById(dto.getReadingId())));
        return entity;
    }
}