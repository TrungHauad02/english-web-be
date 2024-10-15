package com.englishweb.english_web_be.fakedata;

import com.englishweb.english_web_be.model.*;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import com.englishweb.english_web_be.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//@Component
public class DataLoader implements CommandLineRunner {

    private final TopicRepository topicRepository;
    private final VocabularyRepository vocabularyRepository;
    private final TestRepository testRepository;

    private final TopicQuestionRepository topicQuestionRepository;
    private final TopicAnswerRepository topicAnswerRepository;
    private static final TestTypeEnum[] TEST_TYPES = TestTypeEnum.values();
    private final Random random = new Random();
    private  int serialtestmixed=1;

    public DataLoader(TopicRepository topicRepository, TestRepository testRepository,

                      VocabularyRepository vocabularyRepository,
                      TopicQuestionRepository topicQuestionRepository,
                      TopicAnswerRepository topicAnswerRepository) {
        this.topicRepository = topicRepository;
        this.testRepository = testRepository;

        this.vocabularyRepository = vocabularyRepository;
        this.topicQuestionRepository = topicQuestionRepository;
        this.topicAnswerRepository = topicAnswerRepository;
    }

    public void generateTest() {
        for (int i = 100; i >= 1; i--) {

            // Tạo đối tượng Test trước và lưu nó vào database
            String testId = "test_" + i;
            TestTypeEnum type = TEST_TYPES[random.nextInt(TEST_TYPES.length)];
            String title = "Test " + type + " " + i;
            int serial = i;
            int duration = 600;
            StatusEnum status = StatusEnum.ACTIVE;

            Test test = new Test(testId, title, serial, duration, type, status);
            switch (type) {
                case READING:
                    List<TestReading> testReadings = generateTestDataReading(test, i,type);
                    test.setTestReadings(testReadings);
                    break;

                case LISTENING:
                    List<TestListening> testListenings = generateTestDataListening(test, i,type);
                    test.setTestListenings(testListenings);
                    break;

                case SPEAKING:
                    TestSpeaking testSpeaking = generateTestDataSpeaking(test, i,type);
                    test.setTestSpeaking(testSpeaking);
                    break;

                case WRITING:
                    List<TestWriting> testWritings = generateTestDataWriting(test, i,type);
                    test.setTestWritings(testWritings);
                    break;

                case MIXING:
                    serialtestmixed=1;
                    List<TestVocabularyQuestion> mixedVocabQuestions = generateTestDataVocabulary(test, i,type);
                    List<TestGrammarQuestion> mixedGrammarQuestions = generateTestDataGrammar(test, i,type);
                    List<TestReading> mixedReadings = generateTestDataReading(test, i,type);
                    List<TestListening> mixedListenings = generateTestDataListening(test, i,type);
                    List<TestWriting> mixedWritings = generateTestDataWriting(test, i,type);
                    TestSpeaking mixedSpeaking = generateTestDataSpeaking(test, i,type);

                    test.setTestReadings(mixedReadings);
                    test.setTestListenings(mixedListenings);
                    test.setTestVocabularyQuestions(mixedVocabQuestions);
                    test.setTestGrammarQuestions(mixedGrammarQuestions);
                    test.setTestSpeaking(mixedSpeaking);
                    test.setTestWritings(mixedWritings);
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + type);
            }

            testRepository.save(test);
        }
    }
    public List<TestListening> generateTestDataListening(Test test, int i, TestTypeEnum type) {

        List<TestListening> testListenings = new ArrayList<>();
        for (int j = 1; j <= 3; j++) {
            String testListeningId = "listening_" + i + "_" + j;
            String content = "/testlistening" + j + ".mp3";
            String transcript = "Transcript for listening " + j + " of test " + i;
            StatusEnum statusEnum = StatusEnum.ACTIVE;

            TestListening testListening = new TestListening(testListeningId, j, content, transcript, statusEnum);
            testListening.setTest(test); // Liên kết TestListening với Test sau khi Test đã được lưu

            // Tạo các câu hỏi cho TestListening
            List<TestListeningQuestion> questions = new ArrayList<>();
            for (int k = 1; k <= 3; k++) {
                String questionId = "question_" + i + "_" + j + "_" + k;
                String questionContent = "Question " + (j * 3 + k - 3) + " for listening " + j + " of test " + i;
                StatusEnum questionStatus = StatusEnum.ACTIVE;
                int serial;
                if(type==TestTypeEnum.MIXING) {
                    serial = serialtestmixed;
                    serialtestmixed++;
                }
                else {
                    serial =  (j * 3 + k - 3);
                }

                TestListeningQuestion question = new TestListeningQuestion(questionId, serial, questionContent, questionStatus);
                question.setTestListening(testListening);


                List<TestListeningAnswer> answers = new ArrayList<>();
                for (int h = 1; h <= 4; h++) {
                    String answerId = "answer_" + i + "_" + j + "_" + k + "_" + h;
                    String answerContent = "Answer " + h + " for question " + questionId;
                    boolean isCorrect = (h == 1);  // Giả định câu trả lời đầu tiên là đúng
                    StatusEnum answerStatus = StatusEnum.ACTIVE;

                    TestListeningAnswer answer = new TestListeningAnswer(answerId, answerContent, isCorrect, answerStatus);
                    answer.setTestListeningQuestion(question);
                    answers.add(answer);
                }

                question.setAnswersList(answers);
                questions.add(question);
            }

            testListening.setQuestions(questions);
            testListenings.add(testListening);
        }
        return testListenings;
    }

    public  List<TestReading> generateTestDataReading(Test test, int i, TestTypeEnum type) {

            List<TestReading> testReadings = new ArrayList<>();
            for (int j = 1; j <= 3; j++) {
                String testReadingId = "reading_" + i + "_" + j;
                String content = "This is the content for reading " + j + " of test " + i;
                String image = "/images/reading_" + j + ".png";
                StatusEnum statusEnum = StatusEnum.ACTIVE;

                TestReading testReading = new TestReading(testReadingId, j, content, image, statusEnum);
                testReading.setTest(test); // Liên kết TestReading với Test sau khi Test đã được lưu

                // Tạo các câu hỏi cho TestReading
                List<TestReadingQuestion> questions = new ArrayList<>();
                for (int k = 1; k <= 3; k++) {
                    String questionId = "question_" + i + "_" + j + "_" + k;
                    String questionContent = "Question " + (j * 3 + k - 3) + " for reading " + j + " of test " + i;
                    String explanation = "Explanation for question " + (j * 3 + k - 3);
                    int serial;
                    if(type==TestTypeEnum.MIXING) {
                        serial = serialtestmixed;
                        serialtestmixed++;
                    }
                    else {
                        serial =  (j * 3 + k - 3);
                    }
                    StatusEnum questionStatus = StatusEnum.ACTIVE;

                    TestReadingQuestion question = new TestReadingQuestion(questionId, questionContent, serial, explanation, questionStatus);
                    question.setTestReading(testReading);

                    // Tạo các câu trả lời cho câu hỏi
                    List<TestReadingAnswer> answers = new ArrayList<>();
                    for (int h = 1; h <= 4; h++) {
                        String answerId = "answer_" + i + "_" + j + "_" + k + "_" + h;
                        String answerContent = "Answer " + h + " for question " + questionId;
                        boolean isCorrect = (h == 1);  // Giả định câu trả lời đầu tiên là đúng
                        StatusEnum answerStatus = StatusEnum.ACTIVE;

                        TestReadingAnswer answer = new TestReadingAnswer(answerId, answerContent, isCorrect, answerStatus);
                        answer.setTestReadingQuestion(question);
                        answers.add(answer);
                    }

                    question.setAnswers(answers);
                    questions.add(question);
                }

                testReading.setQuestions(questions);
                testReadings.add(testReading);
            }
            return testReadings;
        }



    public TestSpeaking generateTestDataSpeaking(Test test, int i, TestTypeEnum type) {

            String testSpeakingId = "speaking_" + i;
            TestSpeaking testSpeaking = new TestSpeaking(testSpeakingId, "Speaking Title " + i, StatusEnum.ACTIVE);
            testSpeaking.setTest(test); // Liên kết TestSpeaking với Test

            // Tạo các câu hỏi cho TestSpeaking
            List<TestSpeakingQuestion> questions = new ArrayList<>();
            for (int j = 1; j <= 5; j++) {
                String questionId = "speaking_question_" + i + "_" + j;
                String questionContent = "Speaking Question " + j + " for test speaking " + i;
                int serialQuestion;
                if (type==TestTypeEnum.MIXING) {
                    serialQuestion= serialtestmixed;
                    serialtestmixed++;
                }
                else
                {
                    serialQuestion =j;
                }
                StatusEnum questionStatus = StatusEnum.ACTIVE;

                // Tạo đối tượng TestSpeakingQuestion
                TestSpeakingQuestion question = new TestSpeakingQuestion(questionId, questionContent, serialQuestion, questionStatus);
                question.setTestSpeaking(testSpeaking); // Liên kết câu hỏi với TestSpeaking

                questions.add(question);
            }

            // Thiết lập danh sách câu hỏi cho TestSpeaking
            testSpeaking.setQuestions(questions);

            // Lưu đối tượng Test và TestSpeaking
            return testSpeaking;
    }
    public List<TestWriting> generateTestDataWriting(Test test, int i, TestTypeEnum type){

            List<TestWriting> testWritings = new ArrayList<>();
            for (int j = 1; j <= 5; j++) {
                String testWritingId = "writing_" + i + "_" + j;
                String content = "Writing content " + j + " for test writing " + i;
                int serialWriting ;
                if(type==TestTypeEnum.MIXING) {
                    serialWriting = serialtestmixed;
                    serialtestmixed++;
                }
                else
                {
                    serialWriting = j;
                }

                StatusEnum writingStatus = StatusEnum.ACTIVE;

                // Tạo đối tượng TestWriting
                TestWriting testWriting = new TestWriting(testWritingId, serialWriting, content, writingStatus);
                testWriting.setTest(test); // Liên kết TestWriting với Test

                testWritings.add(testWriting);
            }
            return testWritings;

        }
    public List<TestVocabularyQuestion> generateTestDataVocabulary(Test test, int i, TestTypeEnum type) {
        // Tạo danh sách câu hỏi từ vựng cho bài kiểm tra
        List<TestVocabularyQuestion> vocabularyQuestions = new ArrayList<>();

        // Tạo các câu hỏi từ vựng (giả sử có 5 câu hỏi từ vựng)
        for (int j = 1; j <= 5; j++) {
            String questionId = "vocab_question_" + i + "_" + j;
            String questionContent = "Vocabulary question content " + j;
            String explanation = "Explanation for vocabulary question " + j;
            StatusEnum status = StatusEnum.ACTIVE;
            int serial ;
            if(type==TestTypeEnum.MIXING) {
                serial = serialtestmixed;
                serialtestmixed++;
            }
            else
            {
                serial = j;
            }

            // Tạo đối tượng TestVocabularyQuestion
            TestVocabularyQuestion question = new TestVocabularyQuestion(questionId, questionContent, serial, explanation, status);
            question.setTest(test); // Liên kết câu hỏi từ vựng với bài kiểm tra


            List<TestVocabularyAnswer> answers = new ArrayList<>();
            for (int h = 1; h <= 4; h++) {
                String answerId = "vocab_answer_" + j + "_" + h;
                String answerContent = "Answer " + h + " for vocabulary question " + i;
                boolean isCorrect = (h == 1);
                StatusEnum answerStatus = StatusEnum.ACTIVE;

                // Tạo đối tượng TestVocabularyAnswer
                TestVocabularyAnswer answer = new TestVocabularyAnswer(answerId, answerContent, isCorrect, answerStatus);
                answer.setTestVocabularyQuestion(question); // Liên kết câu trả lời với câu hỏi

                answers.add(answer);
            }

            question.setAnswers(answers);


            vocabularyQuestions.add(question);
        }

        return vocabularyQuestions;
    }
    public List<TestGrammarQuestion> generateTestDataGrammar(Test test, int i, TestTypeEnum type) {
        // Tạo danh sách câu hỏi từ vựng cho bài kiểm tra
        List<TestGrammarQuestion> grammarQuestions = new ArrayList<>();

        // Tạo các câu hỏi từ vựng (giả sử có 5 câu hỏi từ vựng)
        for (int j = 1; j <= 5; j++) {
            String questionId = "grammar_question_" + i + "_" + j;
            String questionContent = "Vocabulary question content " + j;
            String explanation = "Explanation for vocabulary question " + j;
            StatusEnum status = StatusEnum.ACTIVE;
            int serial ;
            if(type==TestTypeEnum.MIXING) {
                serial = serialtestmixed;
                serialtestmixed++;
            }
            else
            {
                serial = j;
            }

            // Tạo đối tượng TestVocabularyQuestion
            TestGrammarQuestion question = new TestGrammarQuestion(questionId, questionContent, serial, explanation, status);
            question.setTest(test); // Liên kết câu hỏi từ vựng với bài kiểm tra


            List<TestGrammarAnswer> answers = new ArrayList<>();
            for (int h = 1; h <= 4; h++) {
                String answerId = "vocab_answer_" + j + "_" + h;
                String answerContent = "Answer " + h + " for vocabulary question " + i;
                boolean isCorrect = (h == 1);
                StatusEnum answerStatus = StatusEnum.ACTIVE;

                // Tạo đối tượng TestVocabularyAnswer
                TestGrammarAnswer answer = new TestGrammarAnswer(answerId, answerContent, isCorrect, answerStatus);
                answer.setTestGrammarQuestion(question); // Liên kết câu trả lời với câu hỏi

                answers.add(answer);
            }

            question.setAnswers(answers);


            grammarQuestions.add(question);
        }

        return grammarQuestions;
    }
    @Override
    public void run(String... args) throws Exception {
        generateTest();
    }
}



