package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.TestListeningQuestionDTO;
import com.englishweb.english_web_be.dto.TestReadingAnswerDTO;
import com.englishweb.english_web_be.dto.TestReadingQuestionDTO;
import com.englishweb.english_web_be.dto.request.TestReadingQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.TestReadingAnswerResponseDTO;
import com.englishweb.english_web_be.dto.response.TestReadingQuestionResponseDTO;
import com.englishweb.english_web_be.mapper.TestReadingQuestionMapper;
import com.englishweb.english_web_be.model.TestReadingQuestion;
import com.englishweb.english_web_be.repository.TestReadingQuestionRepository;
import com.englishweb.english_web_be.service.TestReadingQuestionService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TestReadingQuestionServiceImpl extends BaseServiceImpl<TestReadingQuestion, TestReadingQuestionDTO , TestReadingQuestionRequestDTO, TestReadingQuestionResponseDTO, TestReadingQuestionMapper, TestReadingQuestionRepository> implements TestReadingQuestionService {


    private final TestReadingServiceImpl testReadingService;
    private final TestReadingAnswerServiceImpl testReadingAnswerService;

    public TestReadingQuestionServiceImpl(TestReadingQuestionRepository repository,
                                          @Lazy TestReadingServiceImpl testReadingService,
                                          @Lazy TestReadingAnswerServiceImpl testReadingAnswerService,
                                          @Lazy TestReadingQuestionMapper mapper) {
        super(repository, mapper);
        this.testReadingService = testReadingService;
        this.testReadingAnswerService = testReadingAnswerService;
    }


    public List<TestReadingQuestionResponseDTO> findAllByTestReading_Id(String testReadingId) {
        testReadingService.isExist(testReadingId);
        List<TestReadingQuestion> list = repository.findAllByTestReading_Id(testReadingId);

        return list.stream()
                .map(this::convertToDTO)
                .map(mapper::mapToResponseDTO)
                .toList();
    }


    public List<TestReadingQuestionDTO> findAllDTOByTestReading_Id(String testReadingId) {
        testReadingService.isExist(testReadingId);
        List<TestReadingQuestion> list = repository.findAllByTestReading_Id(testReadingId);

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }


    @Override
    protected TestReadingQuestion convertToEntity(TestReadingQuestionDTO dto) {
        TestReadingQuestion entity = new TestReadingQuestion();
        entity.setId(dto.getId());
        entity.setSerial(dto.getSerial());
        entity.setContent(dto.getContent());
        entity.setExplantion(dto.getExplanation());
        entity.setStatus(dto.getStatus());
        entity.setTestReading(testReadingService.convertToEntity(testReadingService.findDTOById(dto.getTestReadingId())));
        return entity;
    }

    @Override
    protected TestReadingQuestionDTO convertToDTO(TestReadingQuestion entity) {
        TestReadingQuestionDTO dto = new TestReadingQuestionDTO();
        dto.setId(entity.getId());
        dto.setSerial(entity.getSerial());
        dto.setContent(entity.getContent());
        dto.setExplanation(entity.getExplantion());
        dto.setStatus(entity.getStatus());
        dto.setTestReadingId(entity.getTestReading().getId());
        dto.setAnswers(testReadingAnswerService.findAllDTOByQuestionId(entity.getId()));
        return dto;
    }
    @Override
    public void delete(String id) {

        List<TestReadingAnswerResponseDTO> answers = testReadingAnswerService.findAllByQuestionId(id);
        if (answers != null) {
            for (TestReadingAnswerResponseDTO answer : answers) {
                testReadingAnswerService.delete(answer.getId());
            }
        }
        super.delete(id);
    }


}
