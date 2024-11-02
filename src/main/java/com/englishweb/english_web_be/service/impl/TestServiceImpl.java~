package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.*;
import com.englishweb.english_web_be.dto.request.TestRequestDTO;
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
    TestMapper mapper;

    public TestServiceImpl(TestRepository repository, @Lazy TestSpeakingServiceImpl testSpeakingService, @Lazy TestReadingServiceImpl testReadingService, @Lazy TestListeningServiceImpl testListeningService, @Lazy TestWritingServiceImpl testWritingService, TestMixingQuestionServiceImpl testMixingQuestionService) {
        super(repository);
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

    public void deleteQuestionTest(String testid, String type, String testdeleteid, int serial) {
//        int questionCount = 1;
//
//        switch (type) {
//            case "VOCABULARY":
//                testMixingQuestionService.delete(testdeleteid);
//            case "GRAMMAR":
//                if (type.equals("GRAMMAR")) {
//                    testMixingQuestionService.delete(testdeleteid);
//                }
//                List<TestMixingQuestionDTO> questionGrammars = testMixingQuestionService.findAllByTestId(testid);
//                List<TestMixingQuestionDTO> questionToUpdateGrammars = questionGrammars.stream()
//                        .filter(q -> q.getSerial() > serial)
//                        .collect(Collectors.toList());
//                for (TestMixingQuestionDTO question : questionToUpdateGrammars) {
//                    question.setSerial(question.getSerial() - 1);
//                    testMixingQuestionService.update(question, question.getId());
//                }
//            case "READING":
//                if (type.equals("READING")) {
//                    questionCount = testReadingService.getTestReadingQuestionService()
//                            .findAllByTestReading_Id(testdeleteid)
//                            .size();
//                    testReadingService.delete(testdeleteid);
//                }
//                List<TestReadingDTO> testReadingDTOs = testReadingService.findAllByTestId(testid);
//                List<TestReadingQuestionDTO> questionReadings = testReadingDTOs.stream()
//                        .flatMap(testReadingDTO -> testReadingDTO.getQuestions().stream())
//                        .collect(Collectors.toList());
//
//                int finalQuestionCount1 = questionCount;
//                questionReadings.stream()
//                        .filter(q -> q.getSerial() > serial)
//                        .forEach(q -> {
//                            q.setSerial(q.getSerial() - finalQuestionCount1);
//                            testReadingService.getTestReadingQuestionService().update(q, q.getId());
//                        });
//            case "LISTENING":
//                if (type.equals("LISTENING")) {
//                    questionCount = testListeningService.getTestListeningQuestionService()
//                            .findAllByTestListening_Id(testdeleteid)
//                            .size();
//                    testListeningService.delete(testdeleteid);
//                }
//                List<TestListeningDTO> testListeningDTOs = testListeningService.findAllByTestId(testid);
//                List<TestListeningQuestionDTO> questionListenings = testListeningDTOs.stream()
//                        .flatMap(testListeningDTO -> testListeningDTO.getQuestions().stream())
//                        .collect(Collectors.toList());
//                int finalQuestionCount = questionCount;
//                questionListenings.stream()
//                        .filter(q -> q.getSerial() > serial)
//                        .forEach(q -> {
//                            q.setSerial(q.getSerial() - finalQuestionCount);
//                            testListeningService.getTestListeningQuestionService().update(q, q.getId());
//                        });
//            case "SPEAKING":
//                if (type.equals("SPEAKING")) {
//                    questionCount = testSpeakingService.getTestSpeakingQuestionService()
//                            .findAllByTestSpeaking_Id(testdeleteid)
//                            .size();
//                    testSpeakingService.delete(testdeleteid);
//                }
//                List<TestSpeakingDTO> testSpeakingDTOs = testSpeakingService.findAllByTest_Id(testid);
//                List<TestSpeakingQuestionDTO> questionSpeakings = testSpeakingDTOs.stream()
//                        .flatMap(testSpeakingDTO -> testSpeakingDTO.getQuestions().stream())
//                        .collect(Collectors.toList());
//
//                int finalQuestionCount2 = questionCount;
//                questionSpeakings.stream()
//                        .filter(q -> q.getSerial() > serial)
//                        .forEach(q -> {
//                            q.setSerial(q.getSerial() - finalQuestionCount2);
//                            testSpeakingService.getTestSpeakingQuestionService().update(q, q.getId());
//                        });
//            case "WRITING":
//                if (type.equals("WRITING")) {
//                    testWritingService.delete(testdeleteid);
//                }
//                List<TestWritingDTO> testWritingDTOs = testWritingService.findAllByTestId(testid);
//                List<TestWritingDTO> testWritingDTOList = testWritingDTOs.stream()
//                        .filter(q -> q.getSerial() > serial)
//                        .collect(Collectors.toList());
//                for (TestWritingDTO testWritingDTO : testWritingDTOList) {
//                    testWritingDTO.setSerial(testWritingDTO.getSerial() - questionCount);
//                    testWritingService.update(testWritingDTO, testWritingDTO.getId());
//                }
//                break;
//
//            default:
//                return;
//        }
    }


    public void addQuestionTest (String testid, String type, Map<String, Object> testadd ){
//        ObjectMapper mapper = new ObjectMapper();
//        int serialquestion = 1;
//        List<TestMixingQuestionDTO> questionVocabularys = testMixingQuestionService.findAllByTest_IdAndType(testid, TestMixingTypeEnum.VOCABULARY);
//
//        int serialquestiongrammar;
//        if (!questionVocabularys.isEmpty()) {
//            serialquestiongrammar = questionVocabularys.get(questionVocabularys.size() - 1).getSerial() + 1;
//        } else {
//            serialquestiongrammar = 1;
//        }
//
//        switch (type) {
//            case "VOCABULARY":
//                TestMixingQuestionDTO vocabularyQuestion = mapper.convertValue(testadd, TestMixingQuestionDTO.class);
//                if (!questionVocabularys.isEmpty()) {
//                    int lastSerial = questionVocabularys.get(questionVocabularys.size() - 1).getSerial();
//                    vocabularyQuestion.setSerial(lastSerial + 1);
//                } else {
//                    vocabularyQuestion.setSerial(serialquestion);
//                }
//                testMixingQuestionService.create(vocabularyQuestion);
//            case "GRAMMAR":
//                if (type.equals("GRAMMAR")) {
//                    List<TestMixingQuestionDTO> questionGrammars = testMixingQuestionService.findAllByTest_IdAndType(testid, TestMixingTypeEnum.GRAMMAR);
//                    TestMixingQuestionDTO grammarQuestion = mapper.convertValue(testadd, TestMixingQuestionDTO.class);
//                    if (!questionGrammars.isEmpty()) {
//                        int lastSerial = questionGrammars.get(questionGrammars.size() - 1).getSerial();
//                        grammarQuestion.setSerial(lastSerial + 1);
//                    } else {
//                        grammarQuestion.setSerial(serialquestiongrammar);
//                    }
//                }
//                else
//                {
//                    List<TestMixingQuestionDTO> questionGrammars = testMixingQuestionService.findAllByTest_IdAndType(testid, TestMixingTypeEnum.valueOf("GRAMMAR"));
//                    for (TestMixingQuestionDTO question : questionGrammars) {
//                        question.setSerial(question.getSerial() + 1);
//                        testMixingQuestionService.update(question, question.getId());
//                    }
//                }
//            case "READING":
//                if (type.equals("READING")) {
//                    testadd.remove("type");
//                    try {
//                        TestReadingDTO readingQuestion = mapper.convertValue(testadd, TestReadingDTO.class);
//                    } catch (IllegalArgumentException e) {
//                        System.out.println("Conversion error: " + e.getMessage());
//                        e.printStackTrace();
//                    }
//                    List<TestReadingDTO> testReadingDTOs = testReadingService.findAllByTestId(testid);
//
//                    TestReadingDTO readingQuestion = mapper.convertValue(testadd, TestReadingDTO.class);
//                    if (!testReadingDTOs.isEmpty()) {
//                        int lastSerial = testReadingDTOs.get(testReadingDTOs.size() - 1).getSerial();
//                        readingQuestion.setSerial(lastSerial + 1);
//                    } else {
//                        readingQuestion.setSerial(1);
//                    }
//                    testReadingService.create(readingQuestion);
//                }
//                else
//                {
//                    List<TestReadingDTO> testReadingDTOs = testReadingService.findAllByTestId(testid);
//                    List<TestReadingQuestionDTO> questionReadings = testReadingDTOs.stream()
//                            .flatMap(testReadingDTO -> testReadingDTO.getQuestions().stream())
//                            .collect(Collectors.toList());
//
//                    for (TestReadingQuestionDTO question : questionReadings) {
//                        question.setSerial(question.getSerial() + 1);
//                        testReadingService.getTestReadingQuestionService().update(question, question.getId());
//                    }
//                }
//
//            case "LISTENING":
//                if (type.equals("LISTENING")) {
//                    testadd.remove("type");
//                    List<TestListeningDTO> testListeningDTOs = testListeningService.findAllByTestId(testid);
//                    TestListeningDTO listeningQuestion = mapper.convertValue(testadd, TestListeningDTO.class);
//                    if (!testListeningDTOs.isEmpty()) {
//                        int lastSerial = testListeningDTOs.get(testListeningDTOs.size() - 1).getSerial();
//                        listeningQuestion.setSerial(lastSerial + 1);
//                    } else {
//                        listeningQuestion.setSerial(1);
//                    }
//                    testListeningService.create(listeningQuestion);
//                }
//                else {
//                    List<TestListeningDTO> testListeningDTOS = testListeningService.findAllByTestId(testid);
//                    List<TestListeningQuestionDTO> questionListenings = testListeningDTOS.stream()
//                            .flatMap(testListeningDTO -> testListeningDTO.getQuestions().stream())
//                            .collect(Collectors.toList());
//
//                    for (TestListeningQuestionDTO question : questionListenings) {
//                        question.setSerial(question.getSerial() + 1);
//                        testListeningService.getTestListeningQuestionService().update(question, question.getId());
//                    }
//                }
//
//            case "SPEAKING":
//                if (type.equals("SPEAKING")) {
//                    testadd.remove("type");
//                    List<TestSpeakingDTO> testSpeakingDTOs = testSpeakingService.findAllByTest_Id(testid);
//                    TestSpeakingDTO speakingQuestion = mapper.convertValue(testadd, TestSpeakingDTO.class);
//                    if (!testSpeakingDTOs.isEmpty()) {
//                        int lastSerial = testSpeakingDTOs.get(testSpeakingDTOs.size() - 1).getSerial();
//                        speakingQuestion.setSerial(lastSerial + 1);
//                    } else {
//                        speakingQuestion.setSerial(1);
//                    }
//                    testSpeakingService.create(speakingQuestion);
//                }
//                else
//                {
//                    List<TestSpeakingDTO> testSpeakingDTOs = testSpeakingService.findAllByTest_Id(testid);
//                    List<TestSpeakingQuestionDTO> questionSpeakings = testSpeakingDTOs.stream()
//                            .flatMap(testSpeakingDTO -> testSpeakingDTO.getQuestions().stream())
//                            .collect(Collectors.toList());
//                    for (TestSpeakingQuestionDTO question : questionSpeakings) {
//                        question.setSerial(question.getSerial() + 1);
//                        testSpeakingService.getTestSpeakingQuestionService().update(question, question.getId());
//                    }
//                }
//
//
//            case "WRITING":
//                if (type.equals("WRITING")) {
//                    testadd.remove("type");
//                    List<TestWritingDTO> testWritingDTOs = testWritingService.findAllByTestId(testid);
//                    TestWritingDTO writingQuestion = mapper.convertValue(testadd, TestWritingDTO.class);
//                    if (!testWritingDTOs.isEmpty()) {
//                        int lastSerial = testWritingDTOs.get(testWritingDTOs.size() - 1).getSerial();
//                        writingQuestion.setSerial(lastSerial + 1);
//                    } else {
//                        writingQuestion.setSerial(1);
//                    }
//                    testWritingService.create(writingQuestion);
//                }
//                else
//                {
//                    List<TestWritingDTO> testWritingDTOs = testWritingService.findAllByTestId(testid);
//
//                    for (TestWritingDTO testWritingDTO : testWritingDTOs) {
//                        testWritingDTO.setSerial(testWritingDTO.getSerial() +1);
//                        testWritingService.update(testWritingDTO, testWritingDTO.getId());
//                    }
//
//                }
//                break;
//
//            default:
//                return;
//        }

    }


    }



