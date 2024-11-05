package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.SpeakingConversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpeakingConversationRepository extends JpaRepository<SpeakingConversation, String> {
    List<SpeakingConversation> findAllBySpeaking_Id(String speakingId);
}
