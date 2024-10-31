package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestSpeakingQuestionDTO;
import com.englishweb.english_web_be.model.TestSpeakingQuestion;
import com.englishweb.english_web_be.repository.TestSpeakingQuestionRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestSpeakingQuestionService extends BaseService<TestSpeakingQuestion, TestSpeakingQuestionDTO, TestSpeakingQuestionRepository> {

    private final TestSpeakingService testSpeakingService;


    public TestSpeakingQuestionService(TestSpeakingQuestionRepository repository,  @Lazy TestSpeakingService testSpeakingService) {
        super(repository);
        this.testSpeakingService = testSpeakingService;
    }
    public List<TestSpeakingQuestionDTO> findAllByTestSpeaking_Id (String testspeaking_id) {
        testSpeakingService.isExist(testspeaking_id);
        List<TestSpeakingQuestion> list = repository.findAllByTestSpeaking_Id(testspeaking_id);

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected TestSpeakingQuestion convertToEntity(TestSpeakingQuestionDTO dto) {
        TestSpeakingQuestion entity = new TestSpeakingQuestion();
        entity.setId(dto.getId());
        entity.setSerial(dto.getSerial());
        entity.setContent(dto.getContent());
        entity.setStatus(dto.getStatus());
        entity.setTestSpeaking(testSpeakingService.convertToEntity(testSpeakingService.findById(dto.getTestSpeakingId())));
        return entity;
    }

    @Override
    protected TestSpeakingQuestionDTO convertToDTO(TestSpeakingQuestion entity) {
        TestSpeakingQuestionDTO dto = new TestSpeakingQuestionDTO();
        dto.setId(entity.getId());
        dto.setSerial(entity.getSerial());
        dto.setContent(entity.getContent());
        dto.setStatus(entity.getStatus());
        dto.setTestSpeakingId(entity.getTestSpeaking().getId());

        return dto;
    }
}
