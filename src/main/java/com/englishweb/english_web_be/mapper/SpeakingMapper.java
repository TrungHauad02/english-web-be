package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.SpeakingDTO;
import com.englishweb.english_web_be.dto.request.SpeakingRequestDTO;
import com.englishweb.english_web_be.dto.response.SpeakingResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class SpeakingMapper implements BaseMapper<SpeakingDTO, SpeakingRequestDTO, SpeakingResponseDTO> {

    @Override
    public SpeakingDTO mapToDTO(SpeakingRequestDTO requestDTO) {
        return SpeakingDTO.builder()
                .id(requestDTO.getId())
                .title(requestDTO.getTitle())
                .serial(requestDTO.getSerial())
                .description(requestDTO.getDescription())
                .image(requestDTO.getImage())
                .topic(requestDTO.getTopic())
                .duration(requestDTO.getDuration())
                .status(requestDTO.getStatus())
                .build();
    }

    @Override
    public SpeakingResponseDTO mapToResponseDTO(SpeakingDTO dto) {
        return SpeakingResponseDTO.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .serial(dto.getSerial())
                .description(dto.getDescription())
                .image(dto.getImage())
                .topic(dto.getTopic())
                .duration(dto.getDuration())
                .status(dto.getStatus())
                .build();
    }
}
