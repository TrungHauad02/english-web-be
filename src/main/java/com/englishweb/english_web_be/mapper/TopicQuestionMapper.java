package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.TopicQuestionDTO;
import com.englishweb.english_web_be.dto.request.TopicQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.TopicQuestionResponseDTO;

public class TopicQuestionMapper implements BaseMapper<TopicQuestionDTO, TopicQuestionRequestDTO, TopicQuestionResponseDTO> {

    @Override
    public TopicQuestionDTO mapToDTO(TopicQuestionRequestDTO requestDTO) {
        return null;
    }

    @Override
    public TopicQuestionResponseDTO mapToResponseDTO(TopicQuestionDTO dto) {
        return null;
    }
}