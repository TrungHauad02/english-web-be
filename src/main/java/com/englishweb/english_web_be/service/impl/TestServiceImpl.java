package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.*;
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
public class TestServiceImpl extends BaseServiceImpl<Test,TestDTO,TestRepository> implements TestService {


    private final TestSpeakingServiceImpl testSpeakingService;
    private final TestReadingServiceImpl testReadingService;
    private final TestListeningServiceImpl testListeningService;
    private final TestWritingServiceImpl testWritingService;
    private final TestMixingQuestionServiceImpl testMixingQuestionService;

    public TestServiceImpl(TestRepository repository, @Lazy TestSpeakingServiceImpl testSpeakingService, @Lazy TestReadingServiceImpl testReadingService, @Lazy TestListeningServiceImpl testListeningService, @Lazy TestWritingServiceImpl testWritingService, TestMixingQuestionServiceImpl testMixingQuestionService) {
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
    public List<TestDTO> retrieveTestsAllByType(String type) {
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

    public void deleteQuestionTest(String testid, String type, String testdeleteid, int serial) {
        int questionCount = 1;

        switch (type) {
            case "VOCABULARY":
                testMixingQuestionService.delete(testdeleteid);
            case "GRAMMAR":
                if (type.equals("GRAMMAR")) {
                    testMixingQuestionService.delete(testdeleteid);
                }
                List<TestMixingQuestionDTO> questionGrammars = testMixingQuestionService.findAllByTestId(testid);
                List<TestMixingQuestionDTO> questionToUpdateGrammars = questionGrammars.stream()
                        .filter(q -> q.getSerial() > serial)
                        .collect(Collectors.toList());
                for (TestMixingQuestionDTO question : questionToUpdateGrammars) {
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
                List<TestReadingDTO> testReadingDTOs = testReadingService.findAllByTestId(testid);
                List<TestReadingQuestionDTO> questionReadings = testReadingDTOs.stream()
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
                List<TestListeningDTO> testListeningDTOs = testListeningService.findAllByTestId(testid);
                List<TestListeningQuestionDTO> questionListenings = testListeningDTOs.stream()
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
                List<TestSpeakingDTO> testSpeakingDTOs = testSpeakingService.findAllByTest_Id(testid);
                List<TestSpeakingQuestionDTO> questionSpeakings = testSpeakingDTOs.stream()
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
                List<TestWritingDTO> testWritingDTOs = testWritingService.findAllByTestId(testid);
                List<TestWritingDTO> testWritingDTOList = testWritingDTOs.stream()
                        .filter(q -> q.getSerial() > serial)
                        .collect(Collectors.toList());
                for (TestWritingDTO testWritingDTO : testWritingDTOList) {
                    testWritingDTO.setSerial(testWritingDTO.getSerial() - questionCount);
                    testWritingService.update(testWritingDTO, testWritingDTO.getId());
                }
                break;

            default:
                return;
        }
    }


    public String addQuestionTest (String testid, String type, Map<String, Object> testadd ){
        ObjectMapper mapper = new ObjectMapper();
        String idnew = "";
        int serialadd = 1;

        switch (type) {
            case "VOCABULARY":
                TestMixingQuestionDTO vocabularyQuestion = mapper.convertValue(testadd, TestMixingQuestionDTO.class);
                idnew = testMixingQuestionService.create(vocabularyQuestion).getId();
            case "GRAMMAR":
                if (type.equals("GRAMMAR")) {
                    TestMixingQuestionDTO grammarQuestion = mapper.convertValue(testadd, TestMixingQuestionDTO.class);
                    idnew= testMixingQuestionService.create(grammarQuestion).getId();
                } else {
                    int finalSerialadd4 = serialadd;
                    List<TestMixingQuestionDTO> testMixingQuestionDTOS = testMixingQuestionService.findAllByTestId(testid);
                    testMixingQuestionDTOS.stream()
                            .filter(question -> TestMixingTypeEnum.GRAMMAR.equals(question.getType()))
                            .forEach(question -> {
                                question.setSerial(question.getSerial() + finalSerialadd4);
                                testMixingQuestionService.update(question, question.getId());
                            });
                }
            case "READING":
                if (type.equals("READING")) {
                    TestReadingDTO testReadingDTO = mapper.convertValue(testadd, TestReadingDTO.class);
                    if ( testReadingDTO.getId().startsWith("temp")) {
                        testReadingService.create(testReadingDTO);
                        serialadd = testReadingDTO.getQuestions().size();

                    }
                    else {
                        TestReadingDTO  testReadingResponseDTO =  testReadingService.update(testReadingDTO, testReadingDTO.getId());
                        serialadd = (int) testReadingDTO.getQuestions()
                                .stream()
                                .filter(question -> question.getId().startsWith("add"))
                                .count()
                                - (int) testReadingDTO.getQuestions()
                                .stream()
                                .filter(question -> question.getId().startsWith("delete"))
                                .count();
                        idnew= testReadingResponseDTO.getId();

                    }
                } else {
                    List<TestReadingDTO> testReadingDTOS = testReadingService.findAllByTestId(testid);
                    List<TestReadingQuestionDTO> questionReadings = testReadingDTOS.stream()
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
                    TestListeningDTO testListeningDTO = mapper.convertValue(testadd, TestListeningDTO.class);
                    idnew = testListeningService.create(testListeningDTO).getId();
                } else {
                    List<TestListeningQuestionDTO> questionListenings = testListeningService.findAllByTestId(testid).stream()
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
                    TestSpeakingDTO speakingQuestion = mapper.convertValue(testadd, TestSpeakingDTO.class);
                    testSpeakingService.create(speakingQuestion);
                    idnew = testSpeakingService.create(speakingQuestion).getId();
                } else {

                    List<TestSpeakingQuestionDTO> questionSpeakings = testSpeakingService.findAllByTest_Id(testid).stream()
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
                    TestWritingDTO writingQuestion = mapper.convertValue(testadd, TestWritingDTO.class);
                    testWritingService.create(writingQuestion);
                    idnew = testWritingService.create(writingQuestion).getId();
                } else {
                    List<TestWritingDTO> testWritingDTOs = testWritingService.findAllByTestId(testid);

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


