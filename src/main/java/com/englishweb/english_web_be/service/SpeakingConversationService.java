package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.SpeakingConversationDTO;

import java.util.List;

public interface SpeakingConversationService extends BaseService<SpeakingConversationDTO> {

    List<SpeakingConversationDTO> findBySpeakingId(String speakingId);
}
