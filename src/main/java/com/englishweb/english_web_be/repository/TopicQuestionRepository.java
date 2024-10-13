package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.TopicQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicQuestionRepository extends JpaRepository<TopicQuestion, String> {
    List<TopicQuestion> findAllByTopic_Id(String topic_id);
}
