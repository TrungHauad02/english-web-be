package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.*;
import com.englishweb.english_web_be.model.*;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import com.englishweb.english_web_be.repository.TestRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class TestService extends BaseService<Test,TestDTO,TestRepository> {


    private final TestSpeakingService testSpeakingService;
    private final TestReadingService testReadingService;
    private final TestListeningService testListeningService;
    private final TestWritingService testWritingService;
    private final TestMixingQuestionService testMixingQuestionService;

    public TestService(TestRepository repository,  @Lazy TestSpeakingService testSpeakingService, @Lazy TestReadingService testReadingService, @Lazy TestListeningService testListeningService, @Lazy TestWritingService testWritingService, TestMixingQuestionService testMixingQuestionService) {
        super(repository);
        this.testSpeakingService = testSpeakingService;
        this.testReadingService = testReadingService;
        this.testListeningService = testListeningService;
        this.testWritingService = testWritingService;
        this.testMixingQuestionService = testMixingQuestionService;
    }


    public Page<TestDTO> retrieveTestsByPage( int page, String type) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("serial"));
        TestTypeEnum testType = TestTypeEnum.valueOf(type.toUpperCase());

        Page<Test> tests = repository.findAllByType(pageable, testType);
        Page<TestDTO> testDTOs = tests.map(this::convertToDTO);
        return testDTOs;
    }
    public List<TestDTO> retrieveTestsallBytype( String type) {

        List<Test> tests = repository.findAllByType(TestTypeEnum.valueOf(type));

        List<TestDTO> testDTOs = tests.stream().map(this::convertToDTO).collect(Collectors.toList());
        return testDTOs;
    }

    @Override
    protected Test convertToEntity(TestDTO dto) {
        Test entity = new Test();
        entity.setId(dto.getId());
        entity.setSerial(dto.getSerial());
        entity.setType(dto.getType());
        entity.setTitle(dto.getTitle());
        entity.setDuration(dto.getDuration());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    protected TestDTO convertToDTO(Test entity) {

        TestDTO dto = new TestDTO();
        dto.setId(entity.getId());
        dto.setSerial(entity.getSerial());
        dto.setType(entity.getType());
        dto.setTitle(entity.getTitle());
        dto.setDuration(entity.getDuration());
        dto.setStatus(entity.getStatus());
        dto.setTestListenings(testListeningService.findAllByTestId(entity.getId()));
        dto.setTestWritings(testWritingService.findAllByTestId(entity.getId()));
        dto.setTestReadings(testReadingService.findAllByTestId(entity.getId()));
        dto.setTestSpeakings(testSpeakingService.findAllByTest_Id(entity.getId()));
        dto.setTestMixingQuestions(testMixingQuestionService.findAllByTestId(entity.getId()));

        return dto;
    }


    @Override
    public void delete(String id) {
        List<TestSpeakingDTO> testSpeakings = testSpeakingService.findAllByTest_Id(id);
        for (TestSpeakingDTO testSpeaking : testSpeakings) {
            testSpeakingService.delete(testSpeaking.getId());
        }

        List<TestReadingDTO> testReadings = testReadingService.findAllByTestId(id);
        for (TestReadingDTO testReading : testReadings) {
            testReadingService.delete(testReading.getId());
        }

        List<TestListeningDTO> testListenings = testListeningService.findAllByTestId(id);
        for (TestListeningDTO testListening : testListenings) {
            testListeningService.delete(testListening.getId());
        }

        List<TestWritingDTO> testWritings = testWritingService.findAllByTestId(id);
        for (TestWritingDTO testWriting : testWritings) {
            testWritingService.delete(testWriting.getId());
        }

        List<TestMixingQuestionDTO> testMixingQuestions = testMixingQuestionService.findAllByTestId(id);
        for (TestMixingQuestionDTO testMixingQuestion : testMixingQuestions) {
            testMixingQuestionService.delete(testMixingQuestion.getId());
        }
        super.delete(id);
    }




}



