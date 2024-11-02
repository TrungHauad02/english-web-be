package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.WritingDTO;
import com.englishweb.english_web_be.dto.request.WritingRequestDTO;
import com.englishweb.english_web_be.dto.response.WritingResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class WritingMapper implements BaseMapper<WritingDTO, WritingRequestDTO, WritingResponseDTO> {

    @Override
    public WritingDTO mapToDTO(WritingRequestDTO requestDTO) {
        return null;
    }

    @Override
    public WritingResponseDTO mapToResponseDTO(WritingDTO dto) {
        return null;
    }
}
