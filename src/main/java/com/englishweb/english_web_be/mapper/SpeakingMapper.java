package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.SpeakingDTO;
import com.englishweb.english_web_be.dto.request.SpeakingRequestDTO;
import com.englishweb.english_web_be.dto.response.SpeakingResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class SpeakingMapper implements BaseMapper<SpeakingDTO, SpeakingRequestDTO, SpeakingResponseDTO> {

    @Override
    public SpeakingDTO mapToDTO(SpeakingRequestDTO requestDTO) {
        return null;
    }

    @Override
    public SpeakingResponseDTO mapToResponseDTO(SpeakingDTO dto) {
        return null;
    }
}
