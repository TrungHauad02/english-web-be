package com.englishweb.english_web_be.repository;

import com.englishweb.english_web_be.model.SpeakingConversation;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpeakingConversationRepository extends JpaRepository<SpeakingConversation, String> {

    List<SpeakingConversation> findAllBySpeaking_Id(String speakingId);

    List<SpeakingConversation> findAllBySpeaking_IdAndStatus(String speakingId, StatusEnum status);
}
