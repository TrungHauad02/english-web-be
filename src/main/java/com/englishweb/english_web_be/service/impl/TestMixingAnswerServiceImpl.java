package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.TestMixingAnswerDTO;
import com.englishweb.english_web_be.dto.request.TestMixingAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.TestMixingAnswerResponseDTO;
import com.englishweb.english_web_be.mapper.TestMixingAnswerMapper;
import com.englishweb.english_web_be.model.TestMixingAnswer;
import com.englishweb.english_web_be.repository.TestMixingAnswerRepository;
import com.englishweb.english_web_be.service.TestMixingAnswerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestMixingAnswerServiceImpl extends BaseServiceImpl<TestMixingAnswer,
        TestMixingAnswerDTO, TestMixingAnswerRequestDTO, TestMixingAnswerResponseDTO, TestMixingAnswerMapper, TestMixingAnswerRepository>
        implements TestMixingAnswerService {

    private final TestMixingQuestionServiceImpl testMixingQuestionService;
    TestMixingAnswerMapper mapper;

    public TestMixingAnswerServiceImpl(TestMixingAnswerRepository repository, @Lazy TestMixingQuestionServiceImpl testMixingQuestionService) {
        super(repository);
        this.testMixingQuestionService = testMixingQuestionService;
    }

    @Override
    public List<TestMixingAnswerResponseDTO> findAllByQuestionId(String questionId) {
        testMixingQuestionService.isExist(questionId);
        List<TestMixingAnswer> list = repository.findAllByTestMixingQuestion_Id(questionId);

        List<TestMixingAnswerDTO> dtoList = list.stream()
                .map(this::convertToDTO)
                .toList();

        return dtoList.stream()
                .map(mapper::mapToResponseDTO)
                .toList();
    }

    @Override
    public List<TestMixingAnswerDTO> findAllDTOByQuestionId(String questionId) {
        testMixingQuestionService.isExist(questionId);
        List<TestMixingAnswer> list = repository.findAllByTestMixingQuestion_Id(questionId);

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected TestMixingAnswer convertToEntity(TestMixingAnswerDTO dto) {
        TestMixingAnswer entity = new TestMixingAnswer();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setIsCorrect(dto.getIsCorrect());
        entity.setStatus(dto.getStatus());
        entity.setTestMixingQuestion(testMixingQuestionService.convertToEntity(
                testMixingQuestionService.findDTOById(dto.getTestQuestionMixingId())));
        return entity;
    }

    @Override
    protected TestMixingAnswerDTO convertToDTO(TestMixingAnswer entity) {
        TestMixingAnswerDTO dto = new TestMixingAnswerDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setIsCorrect(entity.getIsCorrect());
        dto.setStatus(entity.getStatus());
        dto.setTestQuestionMixingId(entity.getTestMixingQuestion().getId());
        return dto;
    }
}
