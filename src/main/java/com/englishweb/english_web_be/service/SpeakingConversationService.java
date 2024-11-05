package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.SpeakingConversationDTO;
import com.englishweb.english_web_be.dto.request.SpeakingConversationRequestDTO;
import com.englishweb.english_web_be.dto.response.SpeakingConversationResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;

import java.util.List;

public interface SpeakingConversationService extends BaseService<SpeakingConversationRequestDTO, SpeakingConversationResponseDTO> {

    List<SpeakingConversationResponseDTO> findBySpeakingId(String speakingId);

    List<SpeakingConversationResponseDTO> findBySpeakingIdAndStatus(String speakingId, StatusEnum status);

    List<SpeakingConversationDTO> findAllDTOBySpeakingId(String speakingId);
}
