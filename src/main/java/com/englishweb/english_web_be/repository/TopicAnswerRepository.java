package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.TopicAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicAnswerRepository extends JpaRepository<TopicAnswer, String> {
    List<TopicAnswer> findAllByQuestion_Id(String questionId);
}
