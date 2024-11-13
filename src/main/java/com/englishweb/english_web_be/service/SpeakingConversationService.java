package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.SpeakingConversationDTO;
import com.englishweb.english_web_be.dto.response.SpeakingConversationResponse;
import com.englishweb.english_web_be.modelenum.StatusEnum;

import java.util.List;

public interface SpeakingConversationService extends BaseService<SpeakingConversationDTO> {

    List<SpeakingConversationDTO> findBySpeakingId(String speakingId);

    List<SpeakingConversationDTO> findBySpeakingIdAndStatus(String speakingId, StatusEnum status);

    SpeakingConversationResponse saveAudio(SpeakingConversationDTO dto);

    void deleteAudio(SpeakingConversationDTO dto);

    String getAudio(SpeakingConversationDTO dto);
}
