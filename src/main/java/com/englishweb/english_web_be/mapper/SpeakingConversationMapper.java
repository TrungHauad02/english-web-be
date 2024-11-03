package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.SpeakingConversationDTO;
import com.englishweb.english_web_be.dto.request.SpeakingConversationRequestDTO;
import com.englishweb.english_web_be.dto.response.SpeakingConversationResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class SpeakingConversationMapper implements BaseMapper<SpeakingConversationDTO, SpeakingConversationRequestDTO, SpeakingConversationResponseDTO> {

    @Override
    public SpeakingConversationDTO mapToDTO(SpeakingConversationRequestDTO requestDTO) {
        return SpeakingConversationDTO.builder()
                .id(requestDTO.getId())
                .name(requestDTO.getName())
                .serial(requestDTO.getSerial())
                .content(requestDTO.getContent())
                .status(requestDTO.getStatus())
                .speakingId(requestDTO.getSpeakingId())
                .build();
    }

    @Override
    public SpeakingConversationResponseDTO mapToResponseDTO(SpeakingConversationDTO dto) {
        return SpeakingConversationResponseDTO.builder()
                .id(dto.getId())
                .name(dto.getName())
                .serial(dto.getSerial())
                .content(dto.getContent())
                .status(dto.getStatus())
                .speakingId(dto.getSpeakingId())
                .build();
    }
}
