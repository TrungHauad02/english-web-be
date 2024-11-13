package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.ReadingAnswerDTO;
import com.englishweb.english_web_be.dto.ReadingQuestionDTO;
import com.englishweb.english_web_be.model.ReadingQuestion;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.ReadingQuestionRepository;
import com.englishweb.english_web_be.service.ReadingQuestionService;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingQuestionServiceImpl extends BaseServiceImpl<ReadingQuestion, ReadingQuestionDTO,
        ReadingQuestionRepository> implements ReadingQuestionService {

    private final ReadingServiceImpl readingService;
    private final ReadingAnswerServiceImpl readingAnswerService;

    public ReadingQuestionServiceImpl(ReadingQuestionRepository repository,
                                      @Lazy ReadingServiceImpl readingService,
                                      ReadingAnswerServiceImpl readingAnswerService) {
        super(repository);
        this.readingService = readingService;
        this.readingAnswerService = readingAnswerService;
    }

    @Override
    public List<ReadingQuestionDTO> findAllByReadingId(String readingId) {
        readingService.isExist(readingId);
        List<ReadingQuestion> entityList = repository.findAllByReading_Id(readingId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<ReadingQuestionDTO> findAllByReadingIdAndStatus(String readingId, StatusEnum status) {
        if(status == null)
            return findAllByReadingId(readingId);
        readingService.isExist(readingId);
        List<ReadingQuestion> entityList = repository.findAllByReading_IdAndStatus(readingId, status);
        return entityList.stream()
                .map(this::convertToDTO)
                .peek(responseDTO -> {
                    List<ReadingAnswerDTO> filteredAnswers = responseDTO.getAnswers().stream()
                            .filter(answer -> answer.getStatus() == status)
                            .toList();
                    responseDTO.setAnswers(filteredAnswers);
                })
                .toList();
    }

    @Override
    public ReadingQuestionDTO create(ReadingQuestionDTO dto) {
        if (isSerialUnique(dto.getReadingId(), dto.getSerial(), null)) {
            throw new DataIntegrityViolationException("Serial " + dto.getSerial() + " must be unique for Reading ID: " + dto.getReadingId());
        }
        return super.create(dto);
    }

    @Override
    public ReadingQuestionDTO update(ReadingQuestionDTO dto, String id) {
        if (isSerialUnique(dto.getReadingId(), dto.getSerial(), id)) {
            throw new DataIntegrityViolationException("Serial " + dto.getSerial() + " must be unique for Reading ID: " + dto.getReadingId());
        }
        return super.update(dto, id);
    }

    private boolean isSerialUnique(String readingId, Integer serial, String excludeId) {
        List<ReadingQuestion> entityList = repository.findAllByReading_Id(readingId);
        return entityList.stream()
                .filter(entity -> !entity.getId().equals(excludeId))
                .anyMatch(entity -> entity.getSerial() == serial);
    }

    @Override
    public void delete(String id) {
        List<ReadingAnswerDTO> answerDTOList = readingAnswerService.findAllByQuestionId(id);
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
        dto.setAnswers(readingAnswerService.findAllByQuestionId(entity.getId()));
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
        entity.setReading(readingService.convertToEntity(readingService.findById(dto.getReadingId())));
        return entity;
    }
}
