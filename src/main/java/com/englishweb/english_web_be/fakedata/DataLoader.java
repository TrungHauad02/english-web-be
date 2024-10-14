package com.englishweb.english_web_be.fakedata;

import com.englishweb.english_web_be.model.*;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import com.englishweb.english_web_be.modelenum.WordTypeEnum;
import com.englishweb.english_web_be.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    private final TopicRepository topicRepository;
    private final VocabularyRepository vocabularyRepository;
    private final TestRepository testRepository;
    private final TopicQuestionRepository topicQuestionRepository;
    private final TopicAnswerRepository topicAnswerRepository;
    private static final TestTypeEnum[] TEST_TYPES = TestTypeEnum.values();
    private final Random random = new Random();
    public DataLoader(TopicRepository topicRepository, TestRepository testRepository, VocabularyRepository vocabularyRepository, TopicQuestionRepository topicQuestionRepository, TopicAnswerRepository topicAnswerRepository) {
        this.vocabularyRepository = vocabularyRepository;
        this.topicRepository = topicRepository;
        this.testRepository = testRepository;
        this.topicQuestionRepository = topicQuestionRepository;
        this.topicAnswerRepository = topicAnswerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 100; i >= 1; i--) {
            String testId = "test_" + i;
            TestTypeEnum type = TEST_TYPES[random.nextInt(TEST_TYPES.length)];
            String title = "Test " + type + i;
            int serial = i; // Sử dụng i làm số thứ tự
            int duration = 30; // Thời gian 30 phút cho mỗi bài test
            StatusEnum status = StatusEnum.ACTIVE;

            testRepository.save(new Test(testId, title, serial, duration, type, status));
        }
    }
}
