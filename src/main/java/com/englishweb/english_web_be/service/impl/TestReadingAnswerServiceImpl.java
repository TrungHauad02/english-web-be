package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.TestReadingAnswerDTO;
import com.englishweb.english_web_be.dto.request.TestReadingAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.TestReadingAnswerResponseDTO;
import com.englishweb.english_web_be.mapper.TestReadingAnswerMapper;
import com.englishweb.english_web_be.model.TestReadingAnswer;
import com.englishweb.english_web_be.repository.TestReadingAnswerRepository;
import com.englishweb.english_web_be.service.TestReadingAnswerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestReadingAnswerServiceImpl extends BaseServiceImpl<TestReadingAnswer, TestReadingAnswerDTO, TestReadingAnswerRequestDTO, TestReadingAnswerResponseDTO, TestReadingAnswerMapper, TestReadingAnswerRepository> implements TestReadingAnswerService {

    private final TestReadingQuestionServiceImpl testReadingQuestionService;
    public TestReadingAnswerServiceImpl(TestReadingAnswerRepository repository,
                                        @Lazy TestReadingQuestionServiceImpl testReadingQuestionService,
                                        @Lazy TestReadingAnswerMapper mapper) {
        super(repository, mapper);
        this.testReadingQuestionService = testReadingQuestionService;
    }

    public List<TestReadingAnswerResponseDTO> findAllByQuestionId(String questionId) {
        testReadingQuestionService.isExist(questionId);
        List<TestReadingAnswer> list = repository.findAllByTestReadingQuestion_Id(questionId);

        if (list.isEmpty()) {
            return null;
        }
        List<TestReadingAnswerDTO> dtoList =   list.stream()
                .map(this::convertToDTO)
                .toList();

        return dtoList.stream()
                .map(mapper::mapToResponseDTO)
                .toList();
    }
    public List<TestReadingAnswerDTO> findAllDTOByQuestionId(String questionId) {
        testReadingQuestionService.isExist(questionId);
        List<TestReadingAnswer> list = repository.findAllByTestReadingQuestion_Id(questionId);

        if (list.isEmpty()) {
            return null;
        }

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected TestReadingAnswer convertToEntity(TestReadingAnswerDTO dto) {
        TestReadingAnswer entity = new TestReadingAnswer();
        entity.setId(dto.getId());
        entity.setIsCorrect(dto.getIsCorrect());
        entity.setContent(dto.getContent());
        entity.setStatus(dto.getStatus());
        entity.setTestReadingQuestion(testReadingQuestionService.convertToEntity(testReadingQuestionService.findDTOById(dto.getTestQuestionReadingId())));
        return entity;
    }

    @Override
    protected TestReadingAnswerDTO convertToDTO(TestReadingAnswer entity) {
        TestReadingAnswerDTO dto = new TestReadingAnswerDTO();
        dto.setId(entity.getId());
        dto.setIsCorrect(entity.getIsCorrect());
        dto.setContent(entity.getContent());
        dto.setStatus(entity.getStatus());
        dto.setTestQuestionReadingId(entity.getTestReadingQuestion().getId());
        return dto;
    }

    @Override
    public void delete(String id) {
        super.delete(id);
    }
}
