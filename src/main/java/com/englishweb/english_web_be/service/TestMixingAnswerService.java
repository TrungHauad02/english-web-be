package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestMixingAnswerDTO;
import com.englishweb.english_web_be.model.TestMixingAnswer;
import com.englishweb.english_web_be.repository.TestMixingAnswerRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestMixingAnswerService extends BaseService<TestMixingAnswer, TestMixingAnswerDTO, TestMixingAnswerRepository> {

    private final TestMixingQuestionService testMixingQuestionService;

    public TestMixingAnswerService(TestMixingAnswerRepository repository,
                                   @Lazy TestMixingQuestionService testMixingQuestionService) {
        super(repository);
        this.testMixingQuestionService = testMixingQuestionService;
    }

    public List<TestMixingAnswerDTO> findAllByQuestionId(String questionId) {
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
        entity.setStatus(dto.getStatus());
        entity.setIsCorrect(dto.getIsCorrect());
        entity.setTestMixingQuestion(testMixingQuestionService.convertToEntity(
                testMixingQuestionService.findById(dto.getTestQuestionMixingId())));
        return entity;
    }

    @Override
    protected TestMixingAnswerDTO convertToDTO(TestMixingAnswer entity) {
        TestMixingAnswerDTO dto = new TestMixingAnswerDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setStatus(entity.getStatus());
        dto.setIsCorrect(entity.getIsCorrect());
        dto.setTestQuestionMixingId(entity.getTestMixingQuestion().getId());
        return dto;
    }
}
