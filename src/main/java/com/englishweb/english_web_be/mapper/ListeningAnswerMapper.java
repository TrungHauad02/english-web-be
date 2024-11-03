package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.ListeningAnswerDTO;
import com.englishweb.english_web_be.dto.request.ListeningAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.ListeningAnswerResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ListeningAnswerMapper implements BaseMapper<ListeningAnswerDTO, ListeningAnswerRequestDTO, ListeningAnswerResponseDTO> {

    @Override
    public ListeningAnswerDTO mapToDTO(ListeningAnswerRequestDTO requestDTO) {
        return ListeningAnswerDTO.builder()
                .id(requestDTO.getId())
                .content(requestDTO.getContent())
                .correct(requestDTO.isCorrect())
                .status(requestDTO.getStatus())
                .questionId(requestDTO.getQuestionId())
                .build();
    }

    @Override
    public ListeningAnswerResponseDTO mapToResponseDTO(ListeningAnswerDTO dto) {
        return ListeningAnswerResponseDTO.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .correct(dto.isCorrect())
                .status(dto.getStatus())
                .questionId(dto.getQuestionId())
                .build();
    }
}
