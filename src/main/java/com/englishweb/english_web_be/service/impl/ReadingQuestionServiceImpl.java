package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.ReadingAnswerDTO;
import com.englishweb.english_web_be.dto.ReadingQuestionDTO;
import com.englishweb.english_web_be.dto.request.ReadingQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.ReadingQuestionResponseDTO;
import com.englishweb.english_web_be.mapper.ReadingQuestionMapper;
import com.englishweb.english_web_be.model.ReadingQuestion;
import com.englishweb.english_web_be.repository.ReadingQuestionRepository;
import com.englishweb.english_web_be.service.ReadingQuestionService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingQuestionServiceImpl extends BaseServiceImpl<ReadingQuestion, ReadingQuestionDTO,
        ReadingQuestionRequestDTO, ReadingQuestionResponseDTO, ReadingQuestionMapper,
        ReadingQuestionRepository> implements ReadingQuestionService {

    private final ReadingServiceImpl readingService;
    private final ReadingAnswerServiceImpl readingAnswerService;

    public ReadingQuestionServiceImpl(ReadingQuestionRepository repository,
                                      @Lazy ReadingServiceImpl readingService,
                                      ReadingAnswerServiceImpl readingAnswerService,
                                      @Lazy ReadingQuestionMapper mapper) {
        super(repository, mapper);
        this.readingService = readingService;
        this.readingAnswerService = readingAnswerService;
    }

    @Override
    public List<ReadingQuestionResponseDTO> findAllByReadingId(String readingId) {
        readingService.isExist(readingId);
        List<ReadingQuestion> entityList = repository.findAllByReading_Id(readingId);
        return entityList.stream()
                .map(this::convertToDTO)
                .map(mapper::mapToResponseDTO)
                .toList();
    }

    @Override
    public List<ReadingQuestionDTO> findAllDTOByReadingId(String readingId) {
        readingService.isExist(readingId);
        List<ReadingQuestion> entityList = repository.findAllByReading_Id(readingId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public void delete(String id) {
        List<ReadingAnswerDTO> answerDTOList = readingAnswerService.findAllDTOByQuestionId(id);
        for (ReadingAnswerDTO answerDTO : answerDTOList) {
            readingAnswerService.delete(answerDTO.getId());
        }
        super.delete(id);
    }

    @Override
    protected ReadingQuestionDTO convertToDTO(ReadingQuestion entity) {
        ReadingQuestionDTO dto = new ReadingQuestionDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setSerial(entity.getSerial());
        dto.setExplanation(entity.getExplanation());
        dto.setStatus(entity.getStatus());
        dto.setReadingId(entity.getReading().getId());
        dto.setAnswers(readingAnswerService.findAllDTOByQuestionId(entity.getId()));
        return dto;
    }

    @Override
    protected ReadingQuestion convertToEntity(ReadingQuestionDTO dto) {
        ReadingQuestion entity = new ReadingQuestion();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setSerial(dto.getSerial());
        entity.setExplanation(dto.getExplanation());
        entity.setStatus(dto.getStatus());
        entity.setReading(readingService.convertToEntity(readingService.findDTOById(dto.getReadingId())));
        return entity;
    }
}
