package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.ReadingQuestionDTO;
import com.englishweb.english_web_be.dto.request.ReadingQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.ReadingQuestionResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ReadingQuestionMapper implements BaseMapper<ReadingQuestionDTO, ReadingQuestionRequestDTO, ReadingQuestionResponseDTO> {

    @Override
    public ReadingQuestionDTO mapToDTO(ReadingQuestionRequestDTO requestDTO) {
        return null;
    }

    @Override
    public ReadingQuestionResponseDTO mapToResponseDTO(ReadingQuestionDTO dto) {
        return null;
    }
}
