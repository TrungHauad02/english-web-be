package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.ReadingQuestionDTO;
import com.englishweb.english_web_be.dto.request.ReadingQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.ReadingQuestionResponseDTO;
import com.englishweb.english_web_be.dto.ReadingAnswerDTO;
import com.englishweb.english_web_be.service.impl.ReadingAnswerServiceImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReadingQuestionMapper implements BaseMapper<ReadingQuestionDTO, ReadingQuestionRequestDTO, ReadingQuestionResponseDTO> {

    private final ReadingAnswerServiceImpl readingAnswerServiceImpl;
    private final ReadingAnswerMapper readingAnswerMapper;

    public ReadingQuestionMapper(ReadingAnswerServiceImpl readingAnswerServiceImpl, ReadingAnswerMapper readingAnswerMapper) {
        this.readingAnswerServiceImpl = readingAnswerServiceImpl;
        this.readingAnswerMapper = readingAnswerMapper;
    }

    @Override
    public ReadingQuestionDTO mapToDTO(ReadingQuestionRequestDTO requestDTO) {
        return ReadingQuestionDTO.builder()
                .id(requestDTO.getId())
                .content(requestDTO.getContent())
                .serial(requestDTO.getSerial())
                .explanation(requestDTO.getExplanation())
                .status(requestDTO.getStatus())
                .readingId(requestDTO.getReadingId())
                .build();
    }

    @Override
    public ReadingQuestionResponseDTO mapToResponseDTO(ReadingQuestionDTO dto) {
        return ReadingQuestionResponseDTO.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .serial(dto.getSerial())
                .explanation(dto.getExplanation())
                .status(dto.getStatus())
                .answers(readingAnswerServiceImpl.findAllDTOByQuestionId(dto.getId())
                        .stream()
                        .map(readingAnswerMapper::mapToResponseDTO)
                        .toList())
                .readingId(dto.getReadingId())
                .build();
    }
}
