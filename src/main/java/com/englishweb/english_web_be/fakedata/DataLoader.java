package com.englishweb.english_web_be.fakedata;

import com.englishweb.english_web_be.model.*;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import com.englishweb.english_web_be.modelenum.WordTypeEnum;
import com.englishweb.english_web_be.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    private final TopicRepository topicRepository;
    private final VocabularyRepository vocabularyRepository;
    private final TestRepository testRepository;
    private final TestListeningRepository testListeningRepository;
    private final TopicQuestionRepository topicQuestionRepository;
    private final TopicAnswerRepository topicAnswerRepository;
    private static final TestTypeEnum[] TEST_TYPES = TestTypeEnum.values();
    private final Random random = new Random();

    public DataLoader(TopicRepository topicRepository, TestRepository testRepository, TestListeningRepository testListeningRepository, VocabularyRepository vocabularyRepository, TopicQuestionRepository topicQuestionRepository, TopicAnswerRepository topicAnswerRepository ) {
        this.topicRepository = topicRepository;
        this.testRepository = testRepository;
        this.testListeningRepository = testListeningRepository;
        this.vocabularyRepository = vocabularyRepository;
        this.topicQuestionRepository = topicQuestionRepository;
        this.topicAnswerRepository = topicAnswerRepository;
    }

    public void generateTestData() {
        for (int i = 100; i >= 1; i--) {

            String testId = "test_" + i;
            TestTypeEnum type = TestTypeEnum.LISTENING;
            String title = "Test " + type + " " + i;
            int serial = i;
            int duration = 600;
            StatusEnum status = StatusEnum.ACTIVE;

            Test test = new Test(testId, title, serial, duration, type, status);
            testRepository.save(test);
            for (int j = 1; j <= 3; j++) {
                String testListeningId = "listening_" + i + "_" + j;
                String content = "/testlistening"+j+".mp3";
                String transcript = "Transcript for listening " + j + " of test " + i;
                StatusEnum statusEnum = StatusEnum.ACTIVE;


                TestListening testListening = new TestListening(testListeningId, j, content, transcript, statusEnum);
                testListening.setTest(test);

                List<TestListeningQuestion> questions = new ArrayList<>();

                for (int k = 1; k <= 3; k++) {
                    String questionId = "question_" + i + "_" + j + "_" + k;
                    String questionContent = "Question " + (j * 3 + k - 3) + " for listening " + j + " of test " + i;
                    StatusEnum questionStatus = StatusEnum.ACTIVE;

                    TestListeningQuestion question = new TestListeningQuestion(questionId, (j * 3 + k - 3), questionContent, questionStatus);
                    question.setTestListening(testListening);

                    List<TestListeningAnswer> answers = new ArrayList<>();
                    for (int h = 1; h <= 4; h++) {
                        String answerId = "answer_" + i + "_" + j + "_" + k + "_" + h;
                        String answerContent = "Answer " + h + " for question " + questionId;
                        boolean isCorrect = (h == 1);
                        StatusEnum answerStatus = StatusEnum.ACTIVE;

                        TestListeningAnswer answer = new TestListeningAnswer(answerId, answerContent, isCorrect, answerStatus);
                        answer.setTestListeningQuestion(question);
                        answers.add(answer);
                    }

                    question.setAnswersList(answers);
                    questions.add(question);
                }

                testListening.setQuestions(questions);

                testListeningRepository.save(testListening);
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 100; i >= 1; i--) {
            String id = "topic_" + i;
            String name = "Environment " + i;
            String imageUrl = "/environment.png";
            String description = "This is environment " + i;
            Topic topic = new Topic(id, name, i, imageUrl, description, StatusEnum.ACTIVE);
            topicRepository.save(topic);
            for(int j = 40; j >= 1; --j){
                String vocabId = "vocab_" + i + j;
                String word = "environment";
                String example = "This is an example for this vocab";
                String meaning = "Môi trường";
                String phonetic = "/environment/";
                String image = "/environment.png";
                WordTypeEnum wordType = WordTypeEnum.NOUN;
                vocabularyRepository.save(new Vocabulary(vocabId, example, image, word, phonetic, meaning, wordType, StatusEnum.ACTIVE, topic));
            }
            for(int j = 0; j < 10; ++j){
                TopicQuestion topicQuestion = new TopicQuestion();
                topicQuestion.setId("topic_question_" + i + j);
                topicQuestion.setContent("What is the answer of this question?");
                topicQuestion.setExplanation("This is the explanation of this question");
                topicQuestion.setSerial(j + 1);
                topicQuestion.setStatus(StatusEnum.ACTIVE);
                topicQuestion.setTopic(topic);
                topicQuestionRepository.save(topicQuestion);
                for(int k = 1; k <= 4; ++k){
                    TopicAnswer topicAnswer = new TopicAnswer();
                    topicAnswer.setId("topic_answer_" + i + j + k);
                    topicAnswer.setContent("This is an answer");
                    topicAnswer.setCorrect(true);
                    topicAnswer.setStatus(StatusEnum.ACTIVE);
                    topicAnswer.setQuestion(topicQuestion);
                    topicAnswerRepository.save(topicAnswer);
                }
            }
        }
        generateTestData();
    }
}
