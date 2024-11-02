package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.SpeakingConversationDTO;
import com.englishweb.english_web_be.dto.request.SpeakingConversationRequestDTO;
import com.englishweb.english_web_be.dto.response.SpeakingConversationResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class SpeakingConversationMapper implements BaseMapper<SpeakingConversationDTO, SpeakingConversationRequestDTO, SpeakingConversationResponseDTO> {

    @Override
    public SpeakingConversationDTO mapToDTO(SpeakingConversationRequestDTO requestDTO) {
        return null;
    }

    @Override
    public SpeakingConversationResponseDTO mapToResponseDTO(SpeakingConversationDTO dto) {
        return null;
    }
}
