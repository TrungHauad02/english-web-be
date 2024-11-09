package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.*;
import com.englishweb.english_web_be.dto.request.*;
import com.englishweb.english_web_be.dto.response.*;
import com.englishweb.english_web_be.mapper.TestMapper;
import com.englishweb.english_web_be.model.*;
import com.englishweb.english_web_be.modelenum.TestMixingTypeEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import com.englishweb.english_web_be.repository.TestRepository;
import com.englishweb.english_web_be.service.TestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
@Service
public class TestServiceImpl extends BaseServiceImpl<Test,TestDTO, TestRequestDTO, TestResponseDTO, TestMapper,TestRepository> implements TestService {


    private final TestSpeakingServiceImpl testSpeakingService;
    private final TestReadingServiceImpl testReadingService;
    private final TestListeningServiceImpl testListeningService;
    private final TestWritingServiceImpl testWritingService;
    private final TestMixingQuestionServiceImpl testMixingQuestionService;


    public TestServiceImpl(TestRepository repository,
                           @Lazy TestSpeakingServiceImpl testSpeakingService,
                           @Lazy TestReadingServiceImpl testReadingService,
                           @Lazy TestListeningServiceImpl testListeningService,
                           @Lazy TestWritingServiceImpl testWritingService,
                           TestMixingQuestionServiceImpl testMixingQuestionService,
                           @Lazy TestMapper mapper) {
        super(repository, mapper);
        this.testSpeakingService = testSpeakingService;
        this.testReadingService = testReadingService;
        this.testListeningService = testListeningService;
        this.testWritingService = testWritingService;
        this.testMixingQuestionService = testMixingQuestionService;
    }


    public Page<TestResponseDTO> retrieveTestsByPage( int page, String type) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("serial"));
        TestTypeEnum testType = TestTypeEnum.valueOf(type.toUpperCase());

        Page<Test> tests = repository.findAllByType(pageable, testType);
        Page<TestResponseDTO> testResponseDTOS = tests.map(this::convertToDTO).map(mapper::mapToResponseDTO);
        return testResponseDTOS;
    }
    public List<TestResponseDTO> retrieveTestsAllByType(String type) {
        List<Test> tests = repository.findAllByType(TestTypeEnum.valueOf(type));
        List<TestResponseDTO> testResponseDTOS = tests.stream().map(this::convertToDTO).map(mapper::mapToResponseDTO).collect(Collectors.toList());
        return testResponseDTOS;
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
        dto.setTestListenings(testListeningService.findAllDTOByTestId(entity.getId()));
        dto.setTestWritings(testWritingService.findAllDTOByTestId(entity.getId()));
        dto.setTestReadings(testReadingService.findAllDTOByTestId(entity.getId()));
        dto.setTestSpeakings(testSpeakingService.findAllDTOByTest_Id(entity.getId()));
        dto.setTestMixingQuestions(testMixingQuestionService.findAllDTOByTestId(entity.getId()));
        return dto;
    }

    @Override
    public void delete(String id) {
        List<TestSpeakingResponseDTO> testSpeakings = testSpeakingService.findAllByTest_Id(id);
        for (TestSpeakingResponseDTO testSpeaking : testSpeakings) {
            testSpeakingService.delete(testSpeaking.getId());
        }

        List<TestReadingResponseDTO> testReadings = testReadingService.findAllByTestId(id);
        for (TestReadingResponseDTO testReading : testReadings) {
            testReadingService.delete(testReading.getId());
        }

        List<TestListeningResponseDTO> testListenings = testListeningService.findAllByTestId(id);
        for (TestListeningResponseDTO testListening : testListenings) {
            testListeningService.delete(testListening.getId());
        }

        List<TestWritingResponseDTO> testWritings = testWritingService.findAllByTestId(id);
        for (TestWritingResponseDTO testWriting : testWritings) {
            testWritingService.delete(testWriting.getId());
        }

        List<TestMixingQuestionResponseDTO> testMixingQuestions = testMixingQuestionService.findAllByTestId(id);
        for (TestMixingQuestionResponseDTO testMixingQuestion : testMixingQuestions) {
            testMixingQuestionService.delete(testMixingQuestion.getId());
        }
        super.delete(id);
    }

    public void deleteQuestionTest(TestRequestDTO testRequestDTO, String type, String testdeleteid, int serial) {
        int questionCount = 1;

        switch (type) {
            case "VOCABULARY":
                testMixingQuestionService.delete(testdeleteid);
            case "GRAMMAR":
                if (type.equals("GRAMMAR")) {
                    testMixingQuestionService.delete(testdeleteid);
                }
                List<TestMixingQuestionRequestDTO> questionToUpdateGrammars = testRequestDTO.getTestMixingQuestions().stream()
                        .filter(q -> q.getSerial() > serial)
                        .collect(Collectors.toList());
                for (TestMixingQuestionRequestDTO question : questionToUpdateGrammars) {
                    question.setSerial(question.getSerial() - 1);
                    testMixingQuestionService.update(question, question.getId());
                }
            case "READING":
                if (type.equals("READING")) {
                    questionCount = testReadingService.getTestReadingQuestionService()
                            .findAllByTestReading_Id(testdeleteid)
                            .size();
                    testReadingService.delete(testdeleteid);
                }
                List<TestReadingRequestDTO> testReadingRequestDTOs = testRequestDTO.getTestReadings();
                List<TestReadingQuestionRequestDTO> questionReadings = testReadingRequestDTOs.stream()
                        .flatMap(testReadingDTO -> testReadingDTO.getQuestions().stream())
                        .collect(Collectors.toList());

                int finalQuestionCount1 = questionCount;
                questionReadings.stream()
                        .filter(q -> q.getSerial() > serial)
                        .forEach(q -> {
                            q.setSerial(q.getSerial() - finalQuestionCount1);
                            testReadingService.getTestReadingQuestionService().update(q, q.getId());
                        });
            case "LISTENING":
                if (type.equals("LISTENING")) {
                    questionCount = testListeningService.getTestListeningQuestionService()
                            .findAllByTestListening_Id(testdeleteid)
                            .size();
                    testListeningService.delete(testdeleteid);
                }
                List<TestListeningRequestDTO> testListeningDTOs = testRequestDTO.getTestListenings();
                List<TestListeningQuestionRequestDTO> questionListenings = testListeningDTOs.stream()
                        .flatMap(testListeningDTO -> testListeningDTO.getQuestions().stream())
                        .collect(Collectors.toList());
                int finalQuestionCount = questionCount;
                questionListenings.stream()
                        .filter(q -> q.getSerial() > serial)
                        .forEach(q -> {
                            q.setSerial(q.getSerial() - finalQuestionCount);
                            testListeningService.getTestListeningQuestionService().update(q, q.getId());
                        });
            case "SPEAKING":
                if (type.equals("SPEAKING")) {
                    questionCount = testSpeakingService.getTestSpeakingQuestionService()
                            .findAllByTestSpeaking_Id(testdeleteid)
                            .size();
                    testSpeakingService.delete(testdeleteid);
                }
                List<TestSpeakingRequestDTO> testSpeakingDTOs = testRequestDTO.getTestSpeakings();
                List<TestSpeakingQuestionRequestDTO> questionSpeakings = testSpeakingDTOs.stream()
                        .flatMap(testSpeakingDTO -> testSpeakingDTO.getQuestions().stream())
                        .collect(Collectors.toList());

                int finalQuestionCount2 = questionCount;
                questionSpeakings.stream()
                        .filter(q -> q.getSerial() > serial)
                        .forEach(q -> {
                            q.setSerial(q.getSerial() - finalQuestionCount2);
                            testSpeakingService.getTestSpeakingQuestionService().update(q, q.getId());
                        });
            case "WRITING":
                if (type.equals("WRITING")) {
                    testWritingService.delete(testdeleteid);
                }
                List<TestWritingRequestDTO> testWritingDTOs = testRequestDTO.getTestWritings();
                List<TestWritingRequestDTO> testWritingDTOList = testWritingDTOs.stream()
                        .filter(q -> q.getSerial() > serial)
                        .collect(Collectors.toList());
                for (TestWritingRequestDTO testWritingDTO : testWritingDTOList) {
                    testWritingDTO.setSerial(testWritingDTO.getSerial() - questionCount);
                    testWritingService.update(testWritingDTO, testWritingDTO.getId());
                }
                break;

            default:
                return;
        }
    }


    public String addQuestionTest(TestRequestDTO testRequestDTO, String type, Map<String, Object> testadd) {
        ObjectMapper objectMapper = new ObjectMapper();
        String idnew = "";
        int serialadd = 1;

        switch (type) {
            case "VOCABULARY":
                TestMixingQuestionRequestDTO vocabularyQuestion = objectMapper.convertValue(testadd, TestMixingQuestionRequestDTO.class);
                idnew = testMixingQuestionService.create(vocabularyQuestion).getId();

            case "GRAMMAR":

                if (type.equals("GRAMMAR")) {
                    TestMixingQuestionRequestDTO grammarQuestion = objectMapper.convertValue(testadd, TestMixingQuestionRequestDTO.class);
                    idnew= testMixingQuestionService.create(grammarQuestion).getId();
                } else {
                    int finalSerialadd4 = serialadd;
                    testRequestDTO.getTestMixingQuestions().stream()
                            .filter(question -> TestMixingTypeEnum.GRAMMAR.equals(question.getType()))
                            .forEach(question -> {
                                question.setSerial(question.getSerial() + finalSerialadd4);
                                testMixingQuestionService.update(question, question.getId());
                            });
                }

            case "READING":
                if (type.equals("READING")) {
                    TestReadingRequestDTO testReadingRequest = objectMapper.convertValue(testadd, TestReadingRequestDTO.class);
                    if ( testReadingRequest.getId().startsWith("temp")) {
                        testReadingService.create(testReadingRequest);
                        serialadd = testReadingRequest.getQuestions().size();

                    }
                    else {
                        testReadingService.update(testReadingRequest, testReadingRequest.getId());
                        serialadd = (int) testReadingRequest.getQuestions()
                                .stream()
                                .filter(question -> question.getId().startsWith("add"))
                                .count()
                                - (int) testReadingRequest.getQuestions()
                                .stream()
                                .filter(question -> question.getId().startsWith("delete"))
                                .count();
                    }
                } else {
                    List<TestReadingQuestionRequestDTO> questionReadings = testRequestDTO.getTestReadings().stream()
                            .flatMap(testReadingDTO -> testReadingDTO.getQuestions().stream())
                            .collect(Collectors.toList());

                    int finalSerialadd3 = serialadd;
                    questionReadings.forEach(question -> {
                        question.setSerial(question.getSerial() + finalSerialadd3);
                        testReadingService.getTestReadingQuestionService().update(question, question.getId());
                    });
                }

            case "LISTENING":
                if (type.equals("LISTENING")) {
                    TestListeningRequestDTO listeningQuestion = objectMapper.convertValue(testadd, TestListeningRequestDTO.class);
                    testListeningService.create(listeningQuestion);
                    idnew = testListeningService.create(listeningQuestion).getId();
                } else {
                    List<TestListeningQuestionRequestDTO> questionListenings = testRequestDTO.getTestListenings().stream()
                            .flatMap(testListeningDTO -> testListeningDTO.getQuestions().stream())
                            .collect(Collectors.toList());

                    int finalSerialadd = serialadd;
                    questionListenings.forEach(question -> {
                        question.setSerial(question.getSerial() + finalSerialadd);
                        testListeningService.getTestListeningQuestionService().update(question, question.getId());
                    });
                }

            case "SPEAKING":
                if (type.equals("SPEAKING")) {
                    TestSpeakingRequestDTO speakingQuestion = objectMapper.convertValue(testadd, TestSpeakingRequestDTO.class);
                    testSpeakingService.create(speakingQuestion);
                    idnew = testSpeakingService.create(speakingQuestion).getId();
                } else {
                    List<TestSpeakingQuestionRequestDTO> questionSpeakings = testRequestDTO.getTestSpeakings().stream()
                            .flatMap(testSpeakingDTO -> testSpeakingDTO.getQuestions().stream())
                            .collect(Collectors.toList());

                    int finalSerialadd1 = serialadd;
                    questionSpeakings.forEach(question -> {
                        question.setSerial(question.getSerial() + finalSerialadd1);
                        testSpeakingService.getTestSpeakingQuestionService().update(question, question.getId());
                    });
                }

            case "WRITING":
                if (type.equals("WRITING")) {
                    TestWritingRequestDTO writingQuestion = objectMapper.convertValue(testadd, TestWritingRequestDTO.class);
                    testWritingService.create(writingQuestion);
                    idnew = testWritingService.create(writingQuestion).getId();
                } else {
                    List<TestWritingRequestDTO> testWritingDTOs = testRequestDTO.getTestWritings();

                    int finalSerialadd2 = serialadd;
                    testWritingDTOs.forEach(testWritingDTO -> {
                        testWritingDTO.setSerial(testWritingDTO.getSerial() + finalSerialadd2);
                        testWritingService.update(testWritingDTO, testWritingDTO.getId());
                    });
                }
                break;
        }
        return idnew;
    }


}



