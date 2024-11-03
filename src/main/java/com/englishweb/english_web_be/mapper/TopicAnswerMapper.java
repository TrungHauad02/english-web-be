package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.TopicAnswerDTO;
import com.englishweb.english_web_be.dto.request.TopicAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.TopicAnswerResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class TopicAnswerMapper implements BaseMapper<TopicAnswerDTO, TopicAnswerRequestDTO, TopicAnswerResponseDTO> {

    @Override
    public TopicAnswerDTO mapToDTO(TopicAnswerRequestDTO requestDTO) {
        return TopicAnswerDTO.builder()
                .id(requestDTO.getId())
                .content(requestDTO.getContent())
                .correct(requestDTO.isCorrect())
                .status(requestDTO.getStatus())
                .questionId(requestDTO.getQuestionId())
                .build();
    }

    @Override
    public TopicAnswerResponseDTO mapToResponseDTO(TopicAnswerDTO dto) {
        return TopicAnswerResponseDTO.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .correct(dto.isCorrect())
                .status(dto.getStatus())
                .questionId(dto.getQuestionId())
                .build();
    }
}
