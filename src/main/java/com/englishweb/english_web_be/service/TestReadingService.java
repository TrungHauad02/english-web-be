package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ReadingDTO;
import com.englishweb.english_web_be.dto.TestReadingDTO;

import com.englishweb.english_web_be.dto.TestReadingQuestionDTO;
import com.englishweb.english_web_be.model.TestReading;
import com.englishweb.english_web_be.model.TestReadingQuestion;
import com.englishweb.english_web_be.repository.TestReadingRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestReadingService extends BaseService<TestReading, TestReadingDTO, TestReadingRepository>{

    private final TestService testService;
    private final TestReadingQuestionService testReadingQuestionService;
    public TestReadingService(TestReadingRepository repository, @Lazy TestService testService, @Lazy TestReadingQuestionService testReadingQuestionService) {
        super(repository);
        this.testService = testService;
        this.testReadingQuestionService = testReadingQuestionService;
    }

    public List<TestReadingDTO> findAllByTestId(String test_id) {
        testService.isExist(test_id);
        List<TestReading> list = repository.findAllByTest_Id(test_id);

        if (list.isEmpty()) {
            return null;
        }

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    protected TestReading convertToEntity(TestReadingDTO dto) {
        TestReading entity = new TestReading();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setSerial(dto.getSerial());
        entity.setImage(dto.getImage());
        entity.setStatus(dto.getStatus());
        entity.setTest(testService.convertToEntity(testService.findById(dto.getTestId())));
        return entity;
    }

    @Override
    protected TestReadingDTO convertToDTO(TestReading entity) {
        TestReadingDTO dto = new TestReadingDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setSerial(entity.getSerial());
        dto.setImage(entity.getImage());
        dto.setStatus(entity.getStatus());
        dto.setQuestions(testReadingQuestionService.findAllByTestReading_Id(entity.getId()));
        dto.setTestId(entity.getTest().getId());
        return dto;
    }
}



