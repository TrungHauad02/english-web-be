package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.ReadingAnswerDTO;
import com.englishweb.english_web_be.dto.request.ReadingAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.ReadingAnswerResponseDTO;
import com.englishweb.english_web_be.mapper.ReadingAnswerMapper;
import com.englishweb.english_web_be.model.ReadingAnswer;
import com.englishweb.english_web_be.repository.ReadingAnswerRepository;
import com.englishweb.english_web_be.service.ReadingAnswerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingAnswerServiceImpl extends BaseServiceImpl<ReadingAnswer, ReadingAnswerDTO,
        ReadingAnswerRequestDTO, ReadingAnswerResponseDTO, ReadingAnswerMapper,
        ReadingAnswerRepository> implements ReadingAnswerService {

    private final ReadingQuestionServiceImpl readingQuestionService;

    public ReadingAnswerServiceImpl(ReadingAnswerRepository repository,
                                    @Lazy ReadingQuestionServiceImpl readingQuestionService,
                                    @Lazy ReadingAnswerMapper mapper) {
        super(repository, mapper);
        this.readingQuestionService = readingQuestionService;
    }

    @Override
    public List<ReadingAnswerResponseDTO> findAllByQuestionId(String questionId) {
        readingQuestionService.isExist(questionId);
        List<ReadingAnswer> entityList = repository.findAllByQuestion_Id(questionId);
        return entityList.stream()
                .map(this::convertToDTO)
                .map(mapper::mapToResponseDTO)
                .toList();
    }

    @Override
    public List<ReadingAnswerDTO> findAllDTOByQuestionId(String questionId) {
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
        entity.setQuestion(readingQuestionService.convertToEntity(readingQuestionService.findDTOById(dto.getQuestionId())));
        return entity;
    }
}
