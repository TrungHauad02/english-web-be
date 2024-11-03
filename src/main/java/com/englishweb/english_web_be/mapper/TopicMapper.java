package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.TopicDTO;
import com.englishweb.english_web_be.dto.request.TopicRequestDTO;
import com.englishweb.english_web_be.dto.response.TopicResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class TopicMapper implements BaseMapper<TopicDTO, TopicRequestDTO, TopicResponseDTO> {

    @Override
    public TopicDTO mapToDTO(TopicRequestDTO topicRequestDTO) {
        return TopicDTO.builder()
                .id(topicRequestDTO.getId())
                .title(topicRequestDTO.getTitle())
                .serial(topicRequestDTO.getSerial())
                .image(topicRequestDTO.getImage())
                .description(topicRequestDTO.getDescription())
                .status(topicRequestDTO.getStatus())
                .build();
    }

    @Override
    public TopicResponseDTO mapToResponseDTO(TopicDTO topicDTO) {
        return TopicResponseDTO.builder()
                .id(topicDTO.getId())
                .title(topicDTO.getTitle())
                .serial(topicDTO.getSerial())
                .image(topicDTO.getImage())
                .description(topicDTO.getDescription())
                .status(topicDTO.getStatus())
                .build();
    }
}
