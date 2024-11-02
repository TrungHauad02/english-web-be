package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.TestReadingQuestionDTO;
import com.englishweb.english_web_be.dto.request.TestReadingQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.TestReadingQuestionResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TestReadingQuestionMapper implements BaseMapper<TestReadingQuestionDTO, TestReadingQuestionRequestDTO, TestReadingQuestionResponseDTO> {

    private final TestReadingAnswerMapper answerMapper;

    public TestReadingQuestionMapper(TestReadingAnswerMapper answerMapper) {
        this.answerMapper = answerMapper;
    }

    @Override
    public TestReadingQuestionDTO mapToDTO(TestReadingQuestionRequestDTO requestDTO) {
        return TestReadingQuestionDTO.builder()
                .id(requestDTO.getId())
                .content(requestDTO.getContent())
                .serial(requestDTO.getSerial())
                .explanation(requestDTO.getExplanation())
                .status(requestDTO.getStatus())
                .testReadingId(requestDTO.getTestReadingId())
                .answers(requestDTO.getAnswers().stream()
                        .map(answerMapper::mapToDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public TestReadingQuestionResponseDTO mapToResponseDTO(TestReadingQuestionDTO dto) {
        return TestReadingQuestionResponseDTO.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .serial(dto.getSerial())
                .explanation(dto.getExplanation())
                .status(dto.getStatus())
                .testReadingId(dto.getTestReadingId())
                .answers(dto.getAnswers().stream()
                        .map(answerMapper::mapToResponseDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
