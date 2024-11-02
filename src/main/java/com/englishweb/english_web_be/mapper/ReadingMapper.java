package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.ReadingDTO;
import com.englishweb.english_web_be.dto.request.ReadingRequestDTO;
import com.englishweb.english_web_be.dto.response.ReadingResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ReadingMapper implements BaseMapper<ReadingDTO, ReadingRequestDTO, ReadingResponseDTO> {

    @Override
    public ReadingDTO mapToDTO(ReadingRequestDTO requestDTO) {
        return null;
    }

    @Override
    public ReadingResponseDTO mapToResponseDTO(ReadingDTO dto) {
        return null;
    }
}
