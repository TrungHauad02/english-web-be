package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.ReadingDTO;
import com.englishweb.english_web_be.dto.request.ReadingRequestDTO;
import com.englishweb.english_web_be.dto.response.ReadingResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ReadingMapper implements BaseMapper<ReadingDTO, ReadingRequestDTO, ReadingResponseDTO> {

    @Override
    public ReadingDTO mapToDTO(ReadingRequestDTO requestDTO) {
        return ReadingDTO.builder()
                .id(requestDTO.getId())
                .title(requestDTO.getTitle())
                .serial(requestDTO.getSerial())
                .description(requestDTO.getDescription())
                .file(requestDTO.getFile())
                .image(requestDTO.getImage())
                .status(requestDTO.getStatus())
                .build();
    }

    @Override
    public ReadingResponseDTO mapToResponseDTO(ReadingDTO dto) {
        return ReadingResponseDTO.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .serial(dto.getSerial())
                .description(dto.getDescription())
                .file(dto.getFile())
                .image(dto.getImage())
                .status(dto.getStatus())
                .build();
    }
}
