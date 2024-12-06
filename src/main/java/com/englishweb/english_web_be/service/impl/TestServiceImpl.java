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
                List<SubmitTestDTO> submitTests = submitTestService.findAllByTestId(id);
                List<String> submitTestStrings = submitTests.stream()
                        .map(SubmitTestDTO::getId)
                        .collect(Collectors.toList());
                testDTO.setSubmitTestsId(submitTestStrings);

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
    public Page<TestDTO> findTestsBySpecification(String title, TestTypeEnum type, int page, int size, StatusEnum status ,String userId,String sortDirection) {

        Pageable pageable = PageRequest.of(page, size,
                Sort.by(Sort.Direction.fromString(sortDirection != null ? sortDirection : "ASC"), "serial")
        );


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
                    if (userId != null ) {
                        dto.setNumberOfQuestions(this.numberOfQuestionTest(test.getId(),test.getType()));
                        dto.setScoreLastOfTest(submitTestService.scoreLastSubmitTest(test.getId(),userId));
                    }
                    if (userId.trim().isEmpty() ) {
                        List<SubmitTestDTO> submitTests = submitTestService.findAllByTestId(test.getId());
                        List<String> submitTestStrings = submitTests.stream()
                                .map(SubmitTestDTO::getId)
                                .collect(Collectors.toList());
                        dto.setSubmitTestsId(submitTestStrings);
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
                numberOfQuestions = testWritingService.serialMaxTestWritingByTestId(testId)+testSpeakingService.totalActiveSpeakingQuestionsByTestId(testId)
                +testListeningService.totalActiveListeningQuestionsByTestId(testId)+ testReadingService.totalReadingQuestionsByTestId(testId)
                +testMixingQuestionService.serialMaxMixingQuestionsByTestId(testId);
                break;
            case READING:
                numberOfQuestions = testReadingService.totalReadingQuestionsByTestId(testId);
                break;

            case LISTENING:
                numberOfQuestions = testListeningService.totalActiveListeningQuestionsByTestId(testId);
                break;

            case SPEAKING:
                numberOfQuestions = testSpeakingService.totalActiveSpeakingQuestionsByTestId(testId);
                break;

            case WRITING:
                numberOfQuestions = testWritingService.serialMaxTestWritingByTestId(testId);
                break;

            default:
                throw new IllegalArgumentException("Unknown test type: " + testType);
        }

        return numberOfQuestions;
    }
    public boolean updateStatus(String id) {
        Test test = testRepository.findById(id).orElse(null);

        if (test != null) {
            if (StatusEnum.ACTIVE.equals(test.getStatus())) {
                test.setStatus(StatusEnum.INACTIVE);
            } else if (StatusEnum.INACTIVE.equals(test.getStatus())) {
                test.setStatus(StatusEnum.ACTIVE);
            } else {
                return false;
            }
            testRepository.save(test);
            return true;
        }
        return false;
    }
    public Integer getMaxSerial() {
        Integer maxSerial = testRepository.findMaxSerial();
        return maxSerial != null ? maxSerial : 0;
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

        List<SubmitTestDTO> submitTestDTOS = submitTestService.findAllByTestId(id);
        if (submitTestDTOS != null && !submitTestDTOS.isEmpty()) {
            for (SubmitTestDTO submitTestDTO : submitTestDTOS) {
                submitTestService.delete(submitTestDTO.getId());
            }
        }

        List<TestSpeakingDTO> testSpeakings = testSpeakingService.findAllByTest_Id(id);
        if (testSpeakings != null && !testSpeakings.isEmpty()) {
            for (TestSpeakingDTO testSpeaking : testSpeakings) {
                testSpeakingService.delete(testSpeaking.getId());
            }
        }

        List<TestReadingDTO> testReadings = testReadingService.findAllByTestId(id);
        if (testReadings != null && !testReadings.isEmpty()) {
            for (TestReadingDTO testReading : testReadings) {
                testReadingService.delete(testReading.getId());
            }
        }

        List<TestListeningDTO> testListenings = testListeningService.findAllByTestId(id);
        if (testListenings != null && !testListenings.isEmpty()) {
            for (TestListeningDTO testListening : testListenings) {
                testListeningService.delete(testListening.getId());
            }
        }

        List<TestWritingDTO> testWritings = testWritingService.findAllByTestId(id);
        if (testWritings != null && !testWritings.isEmpty()) {
            for (TestWritingDTO testWriting : testWritings) {
                testWritingService.delete(testWriting.getId());
            }
        }

        List<TestMixingQuestionDTO> testMixingQuestions = testMixingQuestionService.findAllByTestId(id);
        if (testMixingQuestions != null && !testMixingQuestions.isEmpty()) {
            for (TestMixingQuestionDTO testMixingQuestion : testMixingQuestions) {
                testMixingQuestionService.delete(testMixingQuestion.getId());
            }
        }

        super.delete(id);
    }






}


