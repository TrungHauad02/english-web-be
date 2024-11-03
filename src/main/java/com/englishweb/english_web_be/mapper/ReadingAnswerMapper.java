package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.ReadingAnswerDTO;
import com.englishweb.english_web_be.dto.request.ReadingAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.ReadingAnswerResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ReadingAnswerMapper implements BaseMapper<ReadingAnswerDTO, ReadingAnswerRequestDTO, ReadingAnswerResponseDTO> {

    @Override
    public ReadingAnswerDTO mapToDTO(ReadingAnswerRequestDTO requestDTO) {
        return ReadingAnswerDTO.builder()
                .id(requestDTO.getId())
                .content(requestDTO.getContent())
                .correct(requestDTO.isCorrect())
                .status(requestDTO.getStatus())
                .questionId(requestDTO.getQuestionId())
                .build();
    }

    @Override
    public ReadingAnswerResponseDTO mapToResponseDTO(ReadingAnswerDTO dto) {
        return ReadingAnswerResponseDTO.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .correct(dto.isCorrect())
                .status(dto.getStatus())
                .questionId(dto.getQuestionId())
                .build();
    }
}
