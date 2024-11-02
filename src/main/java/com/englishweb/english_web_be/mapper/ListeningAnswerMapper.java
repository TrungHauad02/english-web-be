package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.ListeningAnswerDTO;
import com.englishweb.english_web_be.dto.request.ListeningAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.ListeningAnswerResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ListeningAnswerMapper implements BaseMapper<ListeningAnswerDTO, ListeningAnswerRequestDTO, ListeningAnswerResponseDTO> {
    @Override
    public ListeningAnswerDTO mapToDTO(ListeningAnswerRequestDTO requestDTO) {
        return null;
    }

    @Override
    public ListeningAnswerResponseDTO mapToResponseDTO(ListeningAnswerDTO dto) {
        return null;
    }
}
