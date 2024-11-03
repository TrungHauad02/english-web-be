package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.TopicQuestion;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicQuestionRepository extends JpaRepository<TopicQuestion, String> {
    List<TopicQuestion> findAllByTopic_Id(String topic_id);

    List<TopicQuestion> findAllByTopic_IdAndStatus(String topic_id, StatusEnum status);
}
