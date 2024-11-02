package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.TestMixingQuestionDTO;
import com.englishweb.english_web_be.dto.request.TestMixingQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.TestMixingQuestionResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestMixingTypeEnum;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TestMixingQuestionMapper implements BaseMapper<TestMixingQuestionDTO, TestMixingQuestionRequestDTO, TestMixingQuestionResponseDTO> {

    private final TestMixingAnswerMapper answerMapper;

    public TestMixingQuestionMapper(TestMixingAnswerMapper answerMapper) {
        this.answerMapper = answerMapper;
    }

    @Override
    public TestMixingQuestionDTO mapToDTO(TestMixingQuestionRequestDTO requestDTO) {
        return TestMixingQuestionDTO.builder()
                .id(requestDTO.getId())
                .content(requestDTO.getContent())
                .serial(requestDTO.getSerial())
                .explanation(requestDTO.getExplanation())
                .type(requestDTO.getType())
                .status(requestDTO.getStatus())
                .testId(requestDTO.getTestId())
                .answers(requestDTO.getAnswers().stream()
                        .map(answerMapper::mapToDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public TestMixingQuestionResponseDTO mapToResponseDTO(TestMixingQuestionDTO dto) {
        return TestMixingQuestionResponseDTO.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .serial(dto.getSerial())
                .explanation(dto.getExplanation())
                .type(dto.getType())
                .status(dto.getStatus())
                .testId(dto.getTestId())
                .answers(dto.getAnswers().stream()
                        .map(answerMapper::mapToResponseDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
