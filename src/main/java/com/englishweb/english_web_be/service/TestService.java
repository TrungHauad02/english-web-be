package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.*;
import com.englishweb.english_web_be.model.Test;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import com.englishweb.english_web_be.repository.TestRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class TestService {
    private  TestRepository repository;

    public TestService(TestRepository repository) {
        this.repository = repository;
    }


    public Page<TestDTO> retrieveTestsByPage( int page, String type) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("serial"));
        TestTypeEnum testType = TestTypeEnum.valueOf(type.toUpperCase());

        Page<Test> tests = repository.findAllByType(pageable, testType);

        Page<TestDTO> testDTOs = tests.map(this::convertToTestDTO);

        return testDTOs;
    }

    private TestDTO convertToTestDTO(Test test) {

        List<TestListeningDTO> testListeningDTOs = test.getTestListenings() != null ?
                test.getTestListenings().stream()
                        .map(testListening -> {

                            List<TestListeningQuestionDTO> questionDTOs = testListening.getQuestions() != null ?
                                    testListening.getQuestions().stream()
                                            .map(question -> {
                                                List<TestListeningAnswerDTO> answerDTOs = question.getAnswersList() != null ?
                                                        question.getAnswersList().stream()
                                                                .map(answer -> new TestListeningAnswerDTO(
                                                                        answer.getId(),
                                                                        answer.getContent(),
                                                                        answer.getIsCorrect(),
                                                                        answer.getStatus()
                                                                )).collect(Collectors.toList())
                                                        : new ArrayList<>();

                                                return new TestListeningQuestionDTO(
                                                        question.getId(),
                                                        question.getContent(),
                                                        question.getSerial(),
                                                        answerDTOs
                                                );
                                            }).collect(Collectors.toList())
                                    : new ArrayList<>();

                            return new TestListeningDTO(
                                    testListening.getId(),
                                    testListening.getSerial(),
                                    testListening.getContent(),
                                    testListening.getTranscript(),
                                    questionDTOs
                            );
                        }).collect(Collectors.toList()) : new ArrayList<>();


        List<TestReadingDTO> testReadingDTOs = test.getTestReadings() != null ?
                test.getTestReadings().stream()
                        .map(testReading -> {
                            List<TestReadingQuestionDTO> questionDTOs = testReading.getQuestions() != null ?
                                    testReading.getQuestions().stream()
                                            .map(question -> {
                                                List<TestReadingAnswerDTO> answerDTOs = question.getAnswers() != null ?
                                                        question.getAnswers().stream()
                                                                .map(answer -> new TestReadingAnswerDTO(
                                                                        answer.getId(),
                                                                        answer.getContent(),
                                                                        answer.getIsCorrect(),
                                                                        answer.getStatus()

                                                                )).collect(Collectors.toList())
                                                        : new ArrayList<>();

                                                return new TestReadingQuestionDTO(
                                                        question.getId(),
                                                        question.getContent(),
                                                        question.getSerial(),
                                                        question.getExplantion(),
                                                        answerDTOs
                                                );
                                            }).collect(Collectors.toList())
                                    : new ArrayList<>();

                            return new TestReadingDTO(
                                    testReading.getId(),
                                    testReading.getSerial(),
                                    testReading.getContent(),
                                    testReading.getImage(),
                                    questionDTOs
                            );
                        }).collect(Collectors.toList()) : new ArrayList<>();

        TestSpeakingDTO testSpeakingDTO = test.getTestSpeaking() != null ?
                new TestSpeakingDTO(
                        test.getTestSpeaking().getId(),
                        test.getTestSpeaking().getTitle(),
                        test.getTestSpeaking().getStatusEnum(),
                        test.getTestSpeaking().getQuestions() != null ?
                                test.getTestSpeaking().getQuestions().stream()
                                        .map(question -> new TestSpeakingQuestionDTO(
                                                question.getId(),
                                                question.getContent(),
                                                question.getSerial(),
                                                question.getStatus()
                                        )).collect(Collectors.toList())
                                : new ArrayList<>()
                ) : null;

        List<TestWritingDTO> testWritingDTOs = test.getTestWritings() != null ?
                test.getTestWritings().stream()
                        .map(writing -> new TestWritingDTO(
                                writing.getId(),
                                writing.getSerial(),
                                writing.getContent(),
                                writing.getStatusEnum()
                        )).collect(Collectors.toList()) : new ArrayList<>();


        List<TestVocabularyQuestionDTO> testVocabularyQuestionDTOs = test.getTestVocabularyQuestions() != null ?
                test.getTestVocabularyQuestions().stream()
                        .map(vocab -> new TestVocabularyQuestionDTO(
                                vocab.getId(),
                                vocab.getContent(),
                                vocab.getSerial(),
                                vocab.getExplantion(),
                                vocab.getStatus(),
                                vocab.getAnswers().stream()
                                        .map(answer -> new TestVocabularyAnswerDTO(
                                                answer.getId(),
                                                answer.getContent(),
                                                answer.getIsCorrect(),
                                                answer.getStatus()
                                        )).collect(Collectors.toList())
                        )).collect(Collectors.toList()) : new ArrayList<>();


        List<TestGrammarQuestionDTO> testGrammarQuestionDTOs = test.getTestGrammarQuestions() != null ?
                test.getTestGrammarQuestions().stream()
                        .map(grammar -> new TestGrammarQuestionDTO(
                                grammar.getId(),
                                grammar.getContent(),
                                grammar.getSerial(),
                                grammar.getExplantion(),
                                grammar.getStatus(),
                                grammar.getAnswers().stream()
                                        .map(answer -> new TestGrammarAnswerDTO(
                                                answer.getId(),
                                                answer.getContent(),
                                                answer.getIsCorrect(),
                                                answer.getStatus()
                                        )).collect(Collectors.toList())
                        )).collect(Collectors.toList()) : new ArrayList<>();


        return new TestDTO(
                test.getId(),
                test.getTitle(),
                test.getSerial(),
                test.getDuration(),
                test.getType(),
                test.getStatus(),
                testListeningDTOs,
                testReadingDTOs,
                testSpeakingDTO,
                testWritingDTOs,
                testVocabularyQuestionDTOs,
                testGrammarQuestionDTOs
        );
    }
}
