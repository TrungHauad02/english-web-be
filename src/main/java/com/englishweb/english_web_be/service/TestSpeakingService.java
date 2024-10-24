package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TestSpeakingDTO;
import com.englishweb.english_web_be.dto.TestWritingDTO;
import com.englishweb.english_web_be.model.Test;
import com.englishweb.english_web_be.model.TestSpeaking;
import com.englishweb.english_web_be.model.TestSpeakingQuestion;
import com.englishweb.english_web_be.model.TestWriting;
import com.englishweb.english_web_be.repository.TestSpeakingRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TestSpeakingService extends BaseService<TestSpeaking, TestSpeakingDTO, TestSpeakingRepository> {

    private final TestService testService;
    private final TestSpeakingQuestionService testSpeakingQuestionService;

    public TestSpeakingService(TestSpeakingRepository repository, @Lazy TestService testService,  @Lazy TestSpeakingQuestionService testSpeakingQuestionService) {
        super(repository);
        this.testService = testService;
        this.testSpeakingQuestionService = testSpeakingQuestionService;
    }

    public TestSpeakingDTO findAllByTest_Id(String test_id) {
        testService.isExist(test_id);
        TestSpeaking testSpeaking = repository.findByTest_Id(test_id);
        if(testSpeaking == null) {
            return null;
        }
        return this.convertToDTO(testSpeaking);
    }

    @Override
    protected TestSpeaking convertToEntity(TestSpeakingDTO dto) {
        TestSpeaking entity = new TestSpeaking();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setStatusEnum(dto.getStatusEnum());
        entity.setTest(testService.convertToEntity(testService.findById(dto.getTestId())));


        return entity;
    }

    @Override
    protected TestSpeakingDTO convertToDTO(TestSpeaking entity) {
        TestSpeakingDTO dto = new TestSpeakingDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setStatusEnum(entity.getStatusEnum());
        dto.setTestId(entity.getTest().getId());
        dto.setQuestions(testSpeakingQuestionService.findAllByTestSpeaking_Id(entity.getId()));
        return dto;
    }
}
