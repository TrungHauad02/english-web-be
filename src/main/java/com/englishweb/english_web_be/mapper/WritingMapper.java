package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.WritingDTO;
import com.englishweb.english_web_be.dto.request.WritingRequestDTO;
import com.englishweb.english_web_be.dto.response.WritingResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class WritingMapper implements BaseMapper<WritingDTO, WritingRequestDTO, WritingResponseDTO> {

    @Override
    public WritingDTO mapToDTO(WritingRequestDTO requestDTO) {
        return WritingDTO.builder()
                .id(requestDTO.getId())
                .title(requestDTO.getTitle())
                .serial(requestDTO.getSerial())
                .description(requestDTO.getDescription())
                .topic(requestDTO.getTopic())
                .image(requestDTO.getImage())
                .status(requestDTO.getStatus())
                .build();
    }

    @Override
    public WritingResponseDTO mapToResponseDTO(WritingDTO dto) {
        return WritingResponseDTO.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .serial(dto.getSerial())
                .description(dto.getDescription())
                .topic(dto.getTopic())
                .image(dto.getImage())
                .status(dto.getStatus())
                .build();
    }
}
