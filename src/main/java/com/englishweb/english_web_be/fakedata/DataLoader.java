package com.englishweb.english_web_be.fakedata;

import com.englishweb.english_web_be.model.*;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestMixingTypeEnum;
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
    private int serialtestmixed = 1;

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
        TestTypeEnum[] testTypes = TestTypeEnum.values();

        for (TestTypeEnum type : testTypes) {
            for (int i = 1; i <= 10; i++) { // Tạo ít dữ liệu hơn để dễ kiểm tra
                String testId = "test_" + type + "_" + i;
                String title = "Test " + type + " " + i;
                int serial = i;
                int duration = 600;
                StatusEnum status = StatusEnum.ACTIVE;

                Test test = new Test(testId, title, serial, duration, type, status);
                switch (type) {
                    case READING:
                        List<TestReading> testReadings = generateTestDataReading(test, i, type);
                        test.setTestReadings(testReadings);
                        break;
                    case LISTENING:
                        List<TestListening> testListenings = generateTestDataListening(test, i, type);
                        test.setTestListenings(testListenings);
                        break;
                    case SPEAKING:
                        List<TestSpeaking> testSpeakings = generateTestDataSpeaking(test, i, type);
                        test.setTestSpeakings(testSpeakings);
                        break;
                    case WRITING:
                        List<TestWriting> testWritings = generateTestDataWriting(test, i, type);
                        test.setTestWritings(testWritings);
                        break;
                    case MIXING:
                        serialtestmixed = 1;
                        test.setTestMixingQuestions(generataTestMixing(test, i, type));
                        List<TestReading> mixedReadings = generateTestDataReading(test, i, type);
                        List<TestListening> mixedListenings = generateTestDataListening(test, i, type);
                        List<TestSpeaking> mixedSpeaking = generateTestDataSpeaking(test, i, type);
                        List<TestWriting> mixedWritings = generateTestDataWriting(test, i, type);

                        test.setTestReadings(mixedReadings);
                        test.setTestListenings(mixedListenings);
                        test.setTestSpeakings(mixedSpeaking);
                        test.setTestWritings(mixedWritings);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + type);
                }
                testRepository.save(test);
            }
        }
    }

    public List<TestListening> generateTestDataListening(Test test, int i, TestTypeEnum type) {
        List<TestListening> testListenings = new ArrayList<>();
        for (int j = 1; j <= 2; j++) {
            String testListeningId = "listening_" + i + "_" + j;
            String content = "/audio/testlistening" + j + ".mp3";
            String transcript = "Transcript: 'The quick brown fox jumps over the lazy dog' - test " + i;
            StatusEnum statusEnum = StatusEnum.ACTIVE;

            TestListening testListening = new TestListening(testListeningId, j, content, transcript, statusEnum);
            testListening.setTest(test);

            List<TestListeningQuestion> questions = new ArrayList<>();
            for (int k = 1; k <= 2; k++) {
                String questionId = "question_" + i + "_" + j + "_" + k;
                String questionContent = "What is the main topic of the audio section " + j + "?";
                int serial = (type == TestTypeEnum.MIXING) ? serialtestmixed++ : (j * 2 + k - 2);
                StatusEnum questionStatus = StatusEnum.ACTIVE;

                TestListeningQuestion question = new TestListeningQuestion(questionId, serial, questionContent, questionStatus);
                question.setTestListening(testListening);

                List<TestListeningAnswer> answers = new ArrayList<>();
                TestListeningAnswer answer1 = new TestListeningAnswer("answer_" + i + "_" + j + "_" + k + "_1", "The benefits of running.", true, StatusEnum.ACTIVE);
                answer1.setTestListeningQuestion(question); // Liên kết câu trả lời với câu hỏi cha
                answers.add(answer1);

                TestListeningAnswer answer2 = new TestListeningAnswer("answer_" + i + "_" + j + "_" + k + "_2", "Cooking tips.", false, StatusEnum.ACTIVE);
                answer2.setTestListeningQuestion(question);
                answers.add(answer2);

                TestListeningAnswer answer3 = new TestListeningAnswer("answer_" + i + "_" + j + "_" + k + "_3", "A historical story.", false, StatusEnum.ACTIVE);
                answer3.setTestListeningQuestion(question);
                answers.add(answer3);

                TestListeningAnswer answer4 = new TestListeningAnswer("answer_" + i + "_" + j + "_" + k + "_4", "A music lesson.", false, StatusEnum.ACTIVE);
                answer4.setTestListeningQuestion(question);
                answers.add(answer4);

                question.setAnswersList(answers);
                questions.add(question);
            }

            testListening.setQuestions(questions);
            testListenings.add(testListening);
        }
        return testListenings;
    }

    public List<TestReading> generateTestDataReading(Test test, int i, TestTypeEnum type) {
        List<TestReading> testReadings = new ArrayList<>();
        for (int j = 1; j <= 2; j++) {
            String testReadingId = "reading_" + i + "_" + j;
            String content = "In the article, 'The Future of Technology', the author discusses various advancements in AI and its impact.";
            String image = "/images/reading_" + j + ".png";
            StatusEnum statusEnum = StatusEnum.ACTIVE;

            TestReading testReading = new TestReading(testReadingId, j, content, image, statusEnum);
            testReading.setTest(test);

            List<TestReadingQuestion> questions = new ArrayList<>();
            for (int k = 1; k <= 2; k++) {
                String questionId = "question_" + i + "_" + j + "_" + k;
                String questionContent = "What does the article suggest about the future of AI?";
                int serial = (type == TestTypeEnum.MIXING) ? serialtestmixed++ : (j * 2 + k - 2);
                String explanation = "AI is expected to revolutionize multiple industries.";
                StatusEnum questionStatus = StatusEnum.ACTIVE;

                TestReadingQuestion question = new TestReadingQuestion(questionId, questionContent, serial, explanation, questionStatus);
                question.setTestReading(testReading);

                List<TestReadingAnswer> answers = new ArrayList<>();
                TestReadingAnswer answer1 = new TestReadingAnswer("answer_" + i + "_" + j + "_" + k + "_1", "It will lead to job creation in tech.", true, StatusEnum.ACTIVE);
                answer1.setTestReadingQuestion(question); // Liên kết câu trả lời với câu hỏi cha
                answers.add(answer1);

                TestReadingAnswer answer2 = new TestReadingAnswer("answer_" + i + "_" + j + "_" + k + "_2", "It will only benefit large corporations.", false, StatusEnum.ACTIVE);
                answer2.setTestReadingQuestion(question);
                answers.add(answer2);

                TestReadingAnswer answer3 = new TestReadingAnswer("answer_" + i + "_" + j + "_" + k + "_3", "AI will replace all human jobs.", false, StatusEnum.ACTIVE);
                answer3.setTestReadingQuestion(question);
                answers.add(answer3);

                TestReadingAnswer answer4 = new TestReadingAnswer("answer_" + i + "_" + j + "_" + k + "_4", "It will be mostly used in agriculture.", false, StatusEnum.ACTIVE);
                answer4.setTestReadingQuestion(question);
                answers.add(answer4);

                question.setAnswers(answers);

                questions.add(question);
            }

            testReading.setQuestions(questions);
            testReadings.add(testReading);
        }
        return testReadings;
    }

    public List<TestSpeaking> generateTestDataSpeaking(Test test, int i, TestTypeEnum type) {
        List<TestSpeaking> testSpeakings = new ArrayList<>();
        for (int k = 1; k <= 2; k++) {
            String testSpeakingId = "speaking_" + i + "_" + k;
            TestSpeaking testSpeaking = new TestSpeaking(testSpeakingId, "Speaking Test " + k, k, StatusEnum.ACTIVE);
            testSpeaking.setTest(test);

            List<TestSpeakingQuestion> questions = new ArrayList<>();
            for (int j = 1; j <= 2; j++) {
                String questionId = "speaking_question_" + i + "_" + k + "_" + j;
                String questionContent = "Describe a place you visited recently and why it was memorable.";
                int serial = (type == TestTypeEnum.MIXING) ? serialtestmixed++ : (k - 1) * 2 + j;
                StatusEnum questionStatus = StatusEnum.ACTIVE;

                TestSpeakingQuestion question = new TestSpeakingQuestion(questionId, questionContent, serial, questionStatus);
                question.setTestSpeaking(testSpeaking);

                questions.add(question);
            }

            testSpeaking.setQuestions(questions);
            testSpeakings.add(testSpeaking);
        }
        return testSpeakings;
    }

    public List<TestWriting> generateTestDataWriting(Test test, int i, TestTypeEnum type) {
        List<TestWriting> testWritings = new ArrayList<>();
        for (int j = 1; j <= 2; j++) {
            String testWritingId = "writing_" + i + "_" + j;
            String content = "Discuss the pros and cons of remote work.";
            int serial = (type == TestTypeEnum.MIXING) ? serialtestmixed++ : j;
            StatusEnum writingStatus = StatusEnum.ACTIVE;

            TestWriting testWriting = new TestWriting(testWritingId, serial, content, writingStatus);
            testWriting.setTest(test);

            testWritings.add(testWriting);
        }
        return testWritings;
    }

    public List<TestMixingQuestion> generataTestMixing(Test test, int i, TestTypeEnum type) {
        List<TestMixingQuestion> mixingQuestions = new ArrayList<>();
        for (int j = 1; j <= 5; j++) {
            String questionId = "mixing_question_" + i + "_" + j;
            String questionContent = (j <= 3) ? "Choose the correct synonym for 'quick'." : "Select the correct usage of 'were' in a sentence.";
            String explanation = "Synonyms help expand vocabulary and understand word meanings.";
            TestMixingTypeEnum testMixingTypeEnum = (j <= 3) ? TestMixingTypeEnum.VOCABULARY : TestMixingTypeEnum.GRAMMAR;
            StatusEnum status = StatusEnum.ACTIVE;
            int serial = serialtestmixed++;

            TestMixingQuestion question = new TestMixingQuestion(questionId, questionContent, serial, explanation, testMixingTypeEnum, status);
            question.setTest(test);
            List<TestMixingAnswer> answers = new ArrayList<>();
            TestMixingAnswer answer1 = new TestMixingAnswer("answer_" + i + "_" + j + "_1", "Fast", j == 1, StatusEnum.ACTIVE);
            answer1.setTestMixingQuestion(question); // Liên kết câu trả lời với câu hỏi cha
            answers.add(answer1);

            TestMixingAnswer answer2 = new TestMixingAnswer("answer_" + i + "_" + j + "_2", "Slow", false, StatusEnum.ACTIVE);
            answer2.setTestMixingQuestion(question);
            answers.add(answer2);

            TestMixingAnswer answer3 = new TestMixingAnswer("answer_" + i + "_" + j + "_3", "Late", false, StatusEnum.ACTIVE);
            answer3.setTestMixingQuestion(question);
            answers.add(answer3);

            TestMixingAnswer answer4 = new TestMixingAnswer("answer_" + i + "_" + j + "_4", "Happy", false, StatusEnum.ACTIVE);
            answer4.setTestMixingQuestion(question);
            answers.add(answer4);

            question.setAnswers(answers);

            question.setAnswers(answers);
            mixingQuestions.add(question);
        }
        return mixingQuestions;
    }

    @Override
    public void run(String... args) throws Exception {
        generateTest();
    }
}
