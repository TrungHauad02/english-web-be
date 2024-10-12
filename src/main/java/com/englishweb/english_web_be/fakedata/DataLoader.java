package com.englishweb.english_web_be.fakedata;

import com.englishweb.english_web_be.model.StatusEnum;
import com.englishweb.english_web_be.model.Topic;
import com.englishweb.english_web_be.model.Vocabulary;
import com.englishweb.english_web_be.model.WordTypeEnum;
import com.englishweb.english_web_be.repository.TopicRepository;
import com.englishweb.english_web_be.repository.VocabularyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final TopicRepository topicRepository;
    private final VocabularyRepository vocabularyRepository;

    public DataLoader(TopicRepository topicRepository, VocabularyRepository vocabularyRepository) {
        this.topicRepository = topicRepository;
        this.vocabularyRepository = vocabularyRepository;
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
    }
}
