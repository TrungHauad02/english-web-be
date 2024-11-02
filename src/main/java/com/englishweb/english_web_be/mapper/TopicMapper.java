package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.TopicDTO;
import com.englishweb.english_web_be.dto.request.TopicRequestDTO;
import com.englishweb.english_web_be.dto.response.TopicResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class TopicMapper implements BaseMapper<TopicDTO, TopicRequestDTO, TopicResponseDTO> {

    @Override
    public TopicDTO mapToDTO(TopicRequestDTO topicRequestDTO) {
        return null;
    }

    @Override
    public TopicResponseDTO mapToResponseDTO(TopicDTO topicDTO) {
        return null;
    }
}
