package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.*;
import com.englishweb.english_web_be.model.*;
import com.englishweb.english_web_be.modelenum.RoleEnum;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestMixingTypeEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import com.englishweb.english_web_be.repository.TestRepository;
import com.englishweb.english_web_be.service.TestService;
import com.englishweb.english_web_be.util.TestSpecification;
import com.englishweb.english_web_be.util.UserSpecification;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class TestServiceImpl extends BaseServiceImpl<Test,TestDTO,TestRepository> implements TestService {


    private final TestSpeakingServiceImpl testSpeakingService;
    private final TestReadingServiceImpl testReadingService;
    private final TestListeningServiceImpl testListeningService;
    private final TestWritingServiceImpl testWritingService;
    private final TestMixingQuestionServiceImpl testMixingQuestionService;
    private final TestRepository testRepository;
    private final SubmitTestServiceImpl submitTestService;

    public TestServiceImpl(TestRepository repository, @Lazy TestSpeakingServiceImpl testSpeakingService, @Lazy TestReadingServiceImpl testReadingService, @Lazy TestListeningServiceImpl testListeningService, @Lazy TestWritingServiceImpl testWritingService, @Lazy TestMixingQuestionServiceImpl testMixingQuestionService, @Lazy SubmitTestServiceImpl submitTestService) {
        super(repository);
        this.testSpeakingService = testSpeakingService;
        this.testReadingService = testReadingService;
        this.testListeningService = testListeningService;
        this.testWritingService = testWritingService;
        this.testMixingQuestionService = testMixingQuestionService;
        this.testRepository = repository;
        this.submitTestService = submitTestService;
    }

    public TestDTO findByIdAndStatus(String id, StatusEnum status) {
        if (status == null) {
            Optional<Test> test = testRepository.findById(id);
            if (test.isPresent()) {
                TestDTO testDTO = convertToDTO(test.get());
                testDTO.setTestMixingQuestions(testMixingQuestionService.findAllByTestId(id));
                testDTO.setTestReadings(testReadingService.findAllByTestId(id));
                testDTO.setTestListenings(testListeningService.findAllByTestId(id));
                testDTO.setTestWritings(testWritingService.findAllByTestId(id));
                testDTO.setTestSpeakings(testSpeakingService.findAllByTest_Id(id));
                return testDTO;
            }
            throw new EntityNotFoundException("Test not found with id: " + id);
        }

        Test test = testRepository.findByIdAndStatus(id, status);
        if (test != null) {
            TestDTO testDTO = convertToDTO(test);
            testDTO.setTestMixingQuestions(testMixingQuestionService.findAllTestMixingQuestionsByTestIdAndStatus(id, status));
            testDTO.setTestReadings(testReadingService.findAllByTestIdAndStatus(id, status));
            testDTO.setTestListenings(testListeningService.findAllByTestIdAndStatus(id, status));
            testDTO.setTestWritings(testWritingService.findAllByTest_IdAAndStatus(id, status));
            testDTO.setTestSpeakings(testSpeakingService.findAllTestSpeakingByTestIdAndStatus(id, status));
            return testDTO;
        }

        throw new EntityNotFoundException("Test not found with id: " + id + " and status: " + status);
    }

    @Override
    public Page<TestDTO> findTestsBySpecification(String title, TestTypeEnum type, int page, int size, StatusEnum status ,String userId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "serial"));

        Specification<Test> spec = Specification.where(TestSpecification.hasTitle(title));
        if(status != null) {
            spec = spec.and(TestSpecification.hasStatus(status));
        }
        if(type!=null) {
            spec = spec.and(TestSpecification.hasType(type));
        }

        Page<Test> testPage = testRepository.findAll( spec,pageable);
        List<TestDTO> dtoList = testPage.getContent().stream()
                .map(test -> {
                    TestDTO dto = convertToDTO(test);
                    if(userId!=null) {
                        dto.setNumberOfQuestions(this.numberOfQuestionTest(test.getId(),test.getType()));
                        dto.setScoreLastOfTest(submitTestService.scoreLastSubmitTest(test.getId(),userId));
                    }
                    return dto;
                })
                .collect(Collectors.toList());
        return new PageImpl<>(dtoList, testPage.getPageable(), testPage.getTotalElements());
    }

    public int numberOfQuestionTest(String testId, TestTypeEnum testType) {
        int numberOfQuestions = 0;

        switch (testType) {
            case MIXING:
                numberOfQuestions = testWritingService.serialMaxTestWritingByTestId(testId);
                if (numberOfQuestions == 0) {
                    numberOfQuestions = testSpeakingService.serialMaxSpeakingQuestionsByTestId(testId);
                    if (numberOfQuestions == 0) {
                        numberOfQuestions = testListeningService.serialMaxListeningQuestionsByTestId(testId);
                        if (numberOfQuestions == 0) {
                            numberOfQuestions = testReadingService.serialMaxReadingQuestionsByTestId(testId);
                            if (numberOfQuestions == 0) {
                                numberOfQuestions = testMixingQuestionService.serialMaxMixingQuestionsByTestId(testId);
                            }
                        }
                    }
                }
                break;
            case READING:
                numberOfQuestions = testReadingService.serialMaxReadingQuestionsByTestId(testId);
                break;

            case LISTENING:
                numberOfQuestions = testListeningService.serialMaxListeningQuestionsByTestId(testId);
                break;

            case SPEAKING:
                numberOfQuestions = testSpeakingService.serialMaxSpeakingQuestionsByTestId(testId);
                break;

            case WRITING:
                numberOfQuestions = testWritingService.serialMaxTestWritingByTestId(testId);
                break;

            default:
                throw new IllegalArgumentException("Unknown test type: " + testType);
        }

        return numberOfQuestions;
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
        List<SubmitTestDTO> submitTestDTOS = submitTestService.findAllByTestId(id);
        for (SubmitTestDTO submitTestDTO : submitTestDTOS) {
            submitTestService.delete(submitTestDTO.getId());
        }

        super.delete(id);
    }





}


