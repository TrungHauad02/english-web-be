package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.TestListeningQuestionDTO;
import com.englishweb.english_web_be.dto.TestReadingDTO;

import com.englishweb.english_web_be.dto.TestReadingQuestionDTO;
import com.englishweb.english_web_be.model.TestReading;
import com.englishweb.english_web_be.model.TestReadingQuestion;
import com.englishweb.english_web_be.repository.TestReadingRepository;
import com.englishweb.english_web_be.service.TestReadingService;
import lombok.Getter;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
@Getter
@Service
public class TestReadingServiceImpl extends BaseServiceImpl<TestReading, TestReadingDTO, TestReadingRepository> implements TestReadingService {

    private final TestServiceImpl testService;
    private final TestReadingQuestionServiceImpl testReadingQuestionService;
    public TestReadingServiceImpl(TestReadingRepository repository, @Lazy TestServiceImpl testService, @Lazy TestReadingQuestionServiceImpl testReadingQuestionService) {
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
    @Override
    public void delete(String id) {

        List<TestReadingQuestionDTO> questions = testReadingQuestionService.findAllByTestReading_Id(id);
        if (questions != null) {
            for (TestReadingQuestionDTO question : questions) {
                testReadingQuestionService.delete(question.getId());
            }
        }
        super.delete(id);
    }
    public void deleteQuestionTest(String questionId) {

        testReadingQuestionService.delete(questionId);
    }

    public void updateQuestionTest(List<TestReadingQuestionDTO> questions) {

        if (questions != null) {
            for (TestReadingQuestionDTO question : questions) {
                testReadingQuestionService.update(question,question.getId());
            }
        }
    }
}



