package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.TopicQuestionDTO;
import com.englishweb.english_web_be.dto.request.TopicQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.TopicQuestionResponseDTO;
import com.englishweb.english_web_be.dto.TopicAnswerDTO;
import com.englishweb.english_web_be.dto.response.TopicAnswerResponseDTO;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class TopicQuestionMapper implements BaseMapper<TopicQuestionDTO, TopicQuestionRequestDTO, TopicQuestionResponseDTO> {

    private final TopicAnswerMapper topicAnswerMapper;

    public TopicQuestionMapper(TopicAnswerMapper topicAnswerMapper) {
        this.topicAnswerMapper = topicAnswerMapper;
    }

    @Override
    public TopicQuestionDTO mapToDTO(TopicQuestionRequestDTO requestDTO) {
        return TopicQuestionDTO.builder()
                .id(requestDTO.getId())
                .content(requestDTO.getContent())
                .serial(requestDTO.getSerial())
                .explanation(requestDTO.getExplanation())
                .status(requestDTO.getStatus())
                .topicId(requestDTO.getTopicId())
                .build();
    }

    @Override
    public TopicQuestionResponseDTO mapToResponseDTO(TopicQuestionDTO dto) {
        return TopicQuestionResponseDTO.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .serial(dto.getSerial())
                .explanation(dto.getExplanation())
                .status(dto.getStatus())
                .answers(dto.getAnswers().stream()
                        .map(topicAnswerMapper::mapToResponseDTO)
                        .collect(Collectors.toList()))
                .topicId(dto.getTopicId())
                .build();
    }
}
