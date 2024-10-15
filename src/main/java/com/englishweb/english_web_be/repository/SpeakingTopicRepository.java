package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.SpeakingTopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakingTopicRepository extends JpaRepository<SpeakingTopic, String> {
    SpeakingTopic findBySpeaking_Id(String speaking_Id);
}
