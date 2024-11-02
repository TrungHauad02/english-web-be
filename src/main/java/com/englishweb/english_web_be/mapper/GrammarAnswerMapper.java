package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.GrammarAnswerDTO;
import com.englishweb.english_web_be.dto.request.GrammarAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.GrammarAnswerResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class GrammarAnswerMapper implements BaseMapper<GrammarAnswerDTO, GrammarAnswerRequestDTO, GrammarAnswerResponseDTO> {
    @Override
    public GrammarAnswerDTO mapToDTO(GrammarAnswerRequestDTO requestDTO) {
        return GrammarAnswerDTO.builder()
                .id(requestDTO.getId())
                .correct(requestDTO.isCorrect())
                .content(requestDTO.getContent())
                .questionId(requestDTO.getQuestionId())
                .status(requestDTO.getStatus())
                .build();
    }

    @Override
    public GrammarAnswerResponseDTO mapToResponseDTO(GrammarAnswerDTO dto) {
        return GrammarAnswerResponseDTO.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .correct(dto.isCorrect())
                .status(dto.getStatus())
                .questionId(dto.getQuestionId())
                .build();
    }
}
