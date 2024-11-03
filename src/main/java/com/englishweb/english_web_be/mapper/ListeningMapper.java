package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.ListeningDTO;
import com.englishweb.english_web_be.dto.request.ListeningRequestDTO;
import com.englishweb.english_web_be.dto.response.ListeningResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ListeningMapper implements BaseMapper<ListeningDTO, ListeningRequestDTO, ListeningResponseDTO> {

    @Override
    public ListeningDTO mapToDTO(ListeningRequestDTO requestDTO) {
        return ListeningDTO.builder()
                .id(requestDTO.getId())
                .title(requestDTO.getTitle())
                .serial(requestDTO.getSerial())
                .description(requestDTO.getDescription())
                .image(requestDTO.getImage())
                .audioUrl(requestDTO.getAudioUrl())
                .status(requestDTO.getStatus())
                .build();
    }

    @Override
    public ListeningResponseDTO mapToResponseDTO(ListeningDTO dto) {
        return ListeningResponseDTO.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .serial(dto.getSerial())
                .description(dto.getDescription())
                .image(dto.getImage())
                .audioUrl(dto.getAudioUrl())
                .status(dto.getStatus())
                .build();
    }
}
