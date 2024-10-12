package com.englishweb.english_web_be.fakedata;

import com.englishweb.english_web_be.model.StatusEnum;
import com.englishweb.english_web_be.model.Test;
import com.englishweb.english_web_be.model.TestTypeEnum;
import com.englishweb.english_web_be.model.Topic;
import com.englishweb.english_web_be.model.Vocabulary;
import com.englishweb.english_web_be.model.WordTypeEnum;
import com.englishweb.english_web_be.repository.TestRepository;
import com.englishweb.english_web_be.repository.TopicRepository;
import com.englishweb.english_web_be.repository.VocabularyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    private final TopicRepository topicRepository;
    private final VocabularyRepository vocabularyRepository;
    private final TestRepository testRepository;
    private static final TestTypeEnum[] TEST_TYPES = TestTypeEnum.values();
    private final Random random = new Random();
    public DataLoader(TopicRepository topicRepository, TestRepository testRepository, VocabularyRepository vocabularyRepository) {
        this.vocabularyRepository = vocabularyRepository;
        this.topicRepository = topicRepository;
        this.testRepository = testRepository;
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
        }
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
