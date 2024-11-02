package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.ReadingAnswerDTO;
import com.englishweb.english_web_be.dto.request.ReadingAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.ReadingAnswerResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ReadingAnswerMapper implements BaseMapper<ReadingAnswerDTO, ReadingAnswerRequestDTO, ReadingAnswerResponseDTO> {

    @Override
    public ReadingAnswerDTO mapToDTO(ReadingAnswerRequestDTO requestDTO) {
        return null;
    }

    @Override
    public ReadingAnswerResponseDTO mapToResponseDTO(ReadingAnswerDTO dto) {
        return null;
    }
}
