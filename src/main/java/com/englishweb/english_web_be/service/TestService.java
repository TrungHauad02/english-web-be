package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.*;
import com.englishweb.english_web_be.model.*;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import com.englishweb.english_web_be.repository.TestRepository;
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


    public TestService(TestRepository repository) {
        super(repository);
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
    protected TestDTO convertToDTO(Test test) {

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
                                                        question.getStatus(),
                                                        answerDTOs
                                                );
                                            }).collect(Collectors.toList())
                                    : new ArrayList<>();

                            return new TestListeningDTO(
                                    testListening.getId(),
                                    testListening.getSerial(),
                                    testListening.getContent(),
                                    testListening.getTranscript(),
                                    testListening.getStatus(),
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
                                                        question.getStatus(),
                                                        answerDTOs
                                                );
                                            }).collect(Collectors.toList())
                                    : new ArrayList<>();

                            return new TestReadingDTO(
                                    testReading.getId(),
                                    testReading.getSerial(),
                                    testReading.getContent(),
                                    testReading.getImage(),
                                    testReading.getStatus(),
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
    @Override
    protected Test convertToEntity(TestDTO testDTO) {

        Test test = new Test(
                testDTO.getId(),
                testDTO.getTitle(),
                testDTO.getSerial(),
                testDTO.getDuration(),
                testDTO.getType(),
                testDTO.getStatus()
        );
        List<TestListening> testListenings = testDTO.getTestListenings() != null ?
                testDTO.getTestListenings().stream()
                        .map(testListeningDTO -> {
                            TestListening testListening = new TestListening(
                                    testListeningDTO.getId(),
                                    testListeningDTO.getSerial(),
                                    testListeningDTO.getContent(),
                                    testListeningDTO.getTranscript(),
                                    testListeningDTO.getStatus()
                            );
                            testListening.setTest(test);

                            List<TestListeningQuestion> questionEntities = testListeningDTO.getQuestions() != null ?
                                    testListeningDTO.getQuestions().stream()
                                            .map(questionDTO -> {
                                                TestListeningQuestion testListeningQuestion = new TestListeningQuestion(
                                                        questionDTO.getId(),
                                                        questionDTO.getSerial(),
                                                        questionDTO.getContent(),
                                                        questionDTO.getStatus()
                                                );
                                                testListeningQuestion.setTestListening(testListening); // Set parent listening for question

                                                List<TestListeningAnswer> answerEntities = questionDTO.getAnswers() != null ?
                                                        questionDTO.getAnswers().stream()
                                                                .map(answerDTO -> {
                                                                    TestListeningAnswer testListeningAnswer = new TestListeningAnswer(
                                                                            answerDTO.getId(),
                                                                            answerDTO.getContent(),
                                                                            answerDTO.getIsCorrect(),
                                                                            answerDTO.getStatus()
                                                                    );
                                                                    testListeningAnswer.setTestListeningQuestion(testListeningQuestion); // Set parent question for answer
                                                                    return testListeningAnswer;
                                                                }).collect(Collectors.toList())
                                                        : new ArrayList<>();

                                                testListeningQuestion.setAnswersList(answerEntities); // Set answers in question
                                                return testListeningQuestion;
                                            }).collect(Collectors.toList())
                                    : new ArrayList<>();

                            testListening.setQuestions(questionEntities); // Set questions in listening
                            return testListening;
                        }).collect(Collectors.toList()) : new ArrayList<>();

        // Convert TestReadingDTO to TestReading entities
        List<TestReading> testReadings = testDTO.getTestReadings() != null ?
                testDTO.getTestReadings().stream()
                        .map(testReadingDTO -> {
                            TestReading testReading = new TestReading(
                                    testReadingDTO.getId(),
                                    testReadingDTO.getSerial(),
                                    testReadingDTO.getContent(),
                                    testReadingDTO.getImage(),
                                    testReadingDTO.getStatus()
                            );
                            testReading.setTest(test); // Set parent test for reading

                            List<TestReadingQuestion> questionEntities = testReadingDTO.getQuestions() != null ?
                                    testReadingDTO.getQuestions().stream()
                                            .map(questionDTO -> {
                                                TestReadingQuestion testReadingQuestion = new TestReadingQuestion(
                                                        questionDTO.getId(),
                                                        questionDTO.getContent(),
                                                        questionDTO.getSerial(),
                                                        questionDTO.getExplanation(),
                                                        questionDTO.getStatus()
                                                );
                                                testReadingQuestion.setTestReading(testReading); // Set parent reading for question

                                                List<TestReadingAnswer> answerEntities = questionDTO.getAnswers() != null ?
                                                        questionDTO.getAnswers().stream()
                                                                .map(answerDTO -> {
                                                                    TestReadingAnswer testReadingAnswer = new TestReadingAnswer(
                                                                            answerDTO.getId(),
                                                                            answerDTO.getContent(),
                                                                            answerDTO.getIsCorrect(),
                                                                            answerDTO.getStatus()
                                                                    );
                                                                    testReadingAnswer.setTestReadingQuestion(testReadingQuestion); // Set parent question for answer
                                                                    return testReadingAnswer;
                                                                }).collect(Collectors.toList())
                                                        : new ArrayList<>();

                                                testReadingQuestion.setAnswers(answerEntities); // Set answers in question
                                                return testReadingQuestion;
                                            }).collect(Collectors.toList())
                                    : new ArrayList<>();

                            testReading.setQuestions(questionEntities); // Set questions in reading
                            return testReading;
                        }).collect(Collectors.toList()) : new ArrayList<>();

        TestSpeaking testSpeaking = testDTO.getTestSpeaking() != null ?
                new TestSpeaking(
                        testDTO.getTestSpeaking().getId(),
                        testDTO.getTestSpeaking().getTitle(),
                        testDTO.getTestSpeaking().getStatusEnum(),
                        new ArrayList<>() // Temporarily set an empty list for the questions
                ) : null;

        if (testSpeaking != null) {
            // Now map the questions and set the parent for each question
            List<TestSpeakingQuestion> speakingQuestions = testDTO.getTestSpeaking().getQuestions() != null ?
                    testDTO.getTestSpeaking().getQuestions().stream()
                            .map(questionDTO -> {
                                TestSpeakingQuestion testSpeakingQuestion = new TestSpeakingQuestion(
                                        questionDTO.getId(),
                                        questionDTO.getContent(),
                                        questionDTO.getSerial(),
                                        questionDTO.getStatus()
                                );
                                testSpeakingQuestion.setTestSpeaking(testSpeaking); // Set parent speaking for question
                                return testSpeakingQuestion;
                            }).collect(Collectors.toList())
                    : new ArrayList<>();

            // Set the questions in TestSpeaking
            testSpeaking.setQuestions(speakingQuestions);

            // Set the parent Test entity for TestSpeaking
            testSpeaking.setTest(test);
        }


        // Convert TestWritingDTO to TestWriting entities
        List<TestWriting> testWritings = testDTO.getTestWritings() != null ?
                testDTO.getTestWritings().stream()
                        .map(writingDTO -> {
                            TestWriting testWriting = new TestWriting(
                                    writingDTO.getId(),
                                    writingDTO.getSerial(),
                                    writingDTO.getContent(),
                                    writingDTO.getStatusEnum()
                            );
                            testWriting.setTest(test); // Set parent test for writing
                            return testWriting;
                        }).collect(Collectors.toList()) : new ArrayList<>();

        // Convert TestVocabularyQuestionDTO to TestVocabularyQuestion entities
        List<TestVocabularyQuestion> testVocabularyQuestions = testDTO.getTestVocabularyQuestions() != null ?
                testDTO.getTestVocabularyQuestions().stream()
                        .map(vocabDTO -> {
                            TestVocabularyQuestion vocabQuestion = new TestVocabularyQuestion(
                                    vocabDTO.getId(),
                                    vocabDTO.getContent(),
                                    vocabDTO.getSerial(),
                                    vocabDTO.getExplantion(),
                                    vocabDTO.getStatus(),
                                    new ArrayList<>() // Temporarily set an empty list for answers
                            );
                            vocabQuestion.setTest(test); // Set parent test for vocabulary question

                            // Now map the answers and set the parent for each answer
                            List<TestVocabularyAnswer> answers = vocabDTO.getAnswers() != null ?
                                    vocabDTO.getAnswers().stream()
                                            .map(answerDTO -> {
                                                TestVocabularyAnswer testVocabularyAnswer = new TestVocabularyAnswer(
                                                        answerDTO.getId(),
                                                        answerDTO.getContent(),
                                                        answerDTO.getIsCorrect(),
                                                        answerDTO.getStatus()
                                                );
                                                testVocabularyAnswer.setTestVocabularyQuestion(vocabQuestion); // Set parent vocabulary question for answer
                                                return testVocabularyAnswer;
                                            }).collect(Collectors.toList())
                                    : new ArrayList<>();

                            // Set the answers back into the vocab question
                            vocabQuestion.setAnswers(answers);

                            return vocabQuestion;
                        }).collect(Collectors.toList()) : new ArrayList<>();
// Convert TestGrammarQuestionDTO to TestGrammarQuestion entities
        List<TestGrammarQuestion> testGrammarQuestions = testDTO.getTestGrammarQuestions() != null ?
                testDTO.getTestGrammarQuestions().stream()
                        .map(grammarDTO -> {
                            TestGrammarQuestion grammarQuestion = new TestGrammarQuestion(
                                    grammarDTO.getId(),
                                    grammarDTO.getContent(),
                                    grammarDTO.getSerial(),
                                    grammarDTO.getExplantion(),
                                    grammarDTO.getStatus(),
                                    new ArrayList<>() // Temporarily set an empty list for answers
                            );
                            grammarQuestion.setTest(test); // Set parent test for grammar question

                            // Now map the answers and set the parent for each answer
                            List<TestGrammarAnswer> answers = grammarDTO.getAnswers() != null ?
                                    grammarDTO.getAnswers().stream()
                                            .map(answerDTO -> {
                                                TestGrammarAnswer testGrammarAnswer = new TestGrammarAnswer(
                                                        answerDTO.getId(),
                                                        answerDTO.getContent(),
                                                        answerDTO.getIsCorrect(),
                                                        answerDTO.getStatus()
                                                );
                                                testGrammarAnswer.setTestGrammarQuestion(grammarQuestion); // Set parent grammar question for answer
                                                return testGrammarAnswer;
                                            }).collect(Collectors.toList())
                                    : new ArrayList<>();

                            // Set the answers back into the grammar question
                            grammarQuestion.setAnswers(answers);

                            return grammarQuestion;
                        }).collect(Collectors.toList()) : new ArrayList<>();


        // Set parent-child relationships for each entity type
        test.setTestListenings(testListenings);
        test.setTestReadings(testReadings);
        test.setTestSpeaking(testSpeaking);
        test.setTestWritings(testWritings);
        test.setTestVocabularyQuestions(testVocabularyQuestions);
        test.setTestGrammarQuestions(testGrammarQuestions);

        return test;
    }



}



