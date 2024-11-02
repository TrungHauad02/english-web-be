package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.TestSpeakingQuestionDTO;
import com.englishweb.english_web_be.dto.request.TestSpeakingQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.TestSpeakingQuestionResponseDTO;
import com.englishweb.english_web_be.mapper.TestSpeakingQuestionMapper;
import com.englishweb.english_web_be.model.TestSpeakingQuestion;
import com.englishweb.english_web_be.repository.TestSpeakingQuestionRepository;
import com.englishweb.english_web_be.service.TestSpeakingQuestionService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestSpeakingQuestionServiceImpl extends BaseServiceImpl<TestSpeakingQuestion, TestSpeakingQuestionDTO, TestSpeakingQuestionRequestDTO, TestSpeakingQuestionResponseDTO, TestSpeakingQuestionMapper, TestSpeakingQuestionRepository> implements TestSpeakingQuestionService {

    private final TestSpeakingServiceImpl testSpeakingService;
    TestSpeakingQuestionMapper mapper;


    public TestSpeakingQuestionServiceImpl(TestSpeakingQuestionRepository repository, @Lazy TestSpeakingServiceImpl testSpeakingService) {
        super(repository);
        this.testSpeakingService = testSpeakingService;
    }
    public List<TestSpeakingQuestionDTO> findAllDTOByTestSpeaking_Id (String testSpeakingId) {
        testSpeakingService.isExist(testSpeakingId);
        List<TestSpeakingQuestion> list = repository.findAllByTestSpeaking_Id(testSpeakingId);

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }
    public List<TestSpeakingQuestionResponseDTO> findAllByTestSpeaking_Id (String testSpeakingId) {
        testSpeakingService.isExist(testSpeakingId);
        List<TestSpeakingQuestion> list = repository.findAllByTestSpeaking_Id(testSpeakingId);

        return list.stream()
                .map(this::convertToDTO)
                .map(mapper::mapToResponseDTO)
                .toList();
    }

    @Override
    protected TestSpeakingQuestion convertToEntity(TestSpeakingQuestionDTO dto) {
        TestSpeakingQuestion entity = new TestSpeakingQuestion();
        entity.setId(dto.getId());
        entity.setSerial(dto.getSerial());
        entity.setContent(dto.getContent());
        entity.setStatus(dto.getStatus());
        entity.setTestSpeaking(testSpeakingService.convertToEntity(testSpeakingService.findDTOById(dto.getTestSpeakingId())));
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
