package com.englishweb.english_web_be.fakedata;

import com.englishweb.english_web_be.model.StatusEnum;
import com.englishweb.english_web_be.model.Topic;
import com.englishweb.english_web_be.repository.TopicRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final TopicRepository topicRepository;

    public DataLoader(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 100; i >= 1; i--) {
            String id = "topic_" + i;
            String name = "Environment " + i;
            String imageUrl = "/environment.png";
            String description = "This is environment " + i;

            topicRepository.save(new Topic(id, name, i, imageUrl, description, StatusEnum.ACTIVE));
        }
    }
}
