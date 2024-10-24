package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestMixingQuestionDTO;
import com.englishweb.english_web_be.model.TestMixingQuestion;
import com.englishweb.english_web_be.repository.TestMixingQuestionRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestMixingQuestionService extends BaseService<TestMixingQuestion, TestMixingQuestionDTO, TestMixingQuestionRepository> {

    private final TestService testService;
    private final TestMixingAnswerService testMixingAnswerService;

    public TestMixingQuestionService(TestMixingQuestionRepository repository,
                                     @Lazy TestService testService,
                                     @Lazy TestMixingAnswerService testMixingAnswerService) {
        super(repository);
        this.testService = testService;
        this.testMixingAnswerService = testMixingAnswerService;
    }

    public List<TestMixingQuestionDTO> findAllByTestId(String testId) {
        testService.isExist(testId);
        List<TestMixingQuestion> list = repository.findAllByTest_Id(testId);

        if (list.isEmpty()) {
            return null;
        }

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected TestMixingQuestion convertToEntity(TestMixingQuestionDTO dto) {
        TestMixingQuestion entity = new TestMixingQuestion();
        entity.setId(dto.getId());
        entity.setSerial(dto.getSerial());
        entity.setContent(dto.getContent());
        entity.setExplanation(dto.getExplanation());
        entity.setStatus(dto.getStatus());
        entity.setType(dto.getType());
        entity.setTest(testService.convertToEntity(testService.findById(dto.getTestId())));
        return entity;
    }

    @Override
    protected TestMixingQuestionDTO convertToDTO(TestMixingQuestion entity) {
        TestMixingQuestionDTO dto = new TestMixingQuestionDTO();
        dto.setId(entity.getId());
        dto.setSerial(entity.getSerial());
        dto.setContent(entity.getContent());
        dto.setExplanation(entity.getExplanation());
        dto.setStatus(entity.getStatus());
        dto.setType(entity.getType());
        dto.setTestId(entity.getTest().getId());
        dto.setAnswers(testMixingAnswerService.findAllByQuestionId(entity.getId()));
        return dto;
    }
}
