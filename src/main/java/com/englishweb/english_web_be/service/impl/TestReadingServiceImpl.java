package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.*;


import com.englishweb.english_web_be.model.TestReading;
import com.englishweb.english_web_be.model.TestReadingQuestion;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.TestReadingRepository;
import com.englishweb.english_web_be.service.TestReadingService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Service
@Slf4j
public class TestReadingServiceImpl extends BaseServiceImpl<TestReading, TestReadingDTO, TestReadingRepository> implements TestReadingService {

    private final TestServiceImpl testService;
    private final TestReadingQuestionServiceImpl testReadingQuestionService;
    private final FirebaseStorageServiceImpl firebaseStorageService;
    public TestReadingServiceImpl(TestReadingRepository repository, @Lazy TestServiceImpl testService, @Lazy TestReadingQuestionServiceImpl testReadingQuestionService, FirebaseStorageServiceImpl firebaseStorageService) {
        super(repository);
        this.testService = testService;
        this.testReadingQuestionService = testReadingQuestionService;
        this.firebaseStorageService = firebaseStorageService;
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
    public List<TestReadingDTO> findAllByTestIdAndStatus(String test_id, StatusEnum status) {
        if (status == null) {
            return findAllByTestId(test_id);
        }

        testService.isExist(test_id);

        List<TestReading> list = repository.findAllByTest_IdAndStatus(test_id, status);

        return list.stream()
                .map(entity -> {
                    TestReadingDTO dto = convertToDTO(entity);
                    dto.setQuestions(testReadingQuestionService.findAllByTestReading_IdAndStatus(entity.getId(),status));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public int totalReadingQuestionsByTestId(String testId) {
        testService.isExist(testId);

        List<TestReading> list = repository.findAllByTest_IdAndStatus(testId, StatusEnum.ACTIVE);

        if (list.isEmpty()) {
            return 0;
        }

        return list.stream()
                .mapToInt(reading -> {
                    List<TestReadingQuestion> questions = reading.getQuestions();
                    if (questions == null) {
                        return 0;
                    }
                    return (int) questions.stream()
                            .filter(question -> question.getStatus() == StatusEnum.ACTIVE)
                            .count();
                })
                .sum();
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
        TestReadingDTO dto = super.findById(id);
        try {
            firebaseStorageService.deleteFile(dto.getImage());

        } catch (IOException e) {
            log.error("Error occurred while deleting audio for TestReading with ID: {}", dto.getId(), e);
        }
        List<TestReadingQuestionDTO> questions = testReadingQuestionService.findAllByTestReading_Id(id);
        if (questions != null) {
            for (TestReadingQuestionDTO question : questions) {
                testReadingQuestionService.delete(question.getId());
            }
        }
        super.delete(id);
    }
}


