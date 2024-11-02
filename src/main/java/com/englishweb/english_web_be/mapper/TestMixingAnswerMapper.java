package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.TestMixingAnswerDTO;
import com.englishweb.english_web_be.dto.request.TestMixingAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.TestMixingAnswerResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.stereotype.Component;

@Component
public class TestMixingAnswerMapper implements BaseMapper<TestMixingAnswerDTO, TestMixingAnswerRequestDTO, TestMixingAnswerResponseDTO> {

    @Override
    public TestMixingAnswerDTO mapToDTO(TestMixingAnswerRequestDTO requestDTO) {
        return TestMixingAnswerDTO.builder()
                .id(requestDTO.getId())
                .content(requestDTO.getContent())
                .isCorrect(requestDTO.getIsCorrect())
                .status(requestDTO.getStatus())
                .testQuestionMixingId(requestDTO.getTestQuestionMixingId())
                .build();
    }

    @Override
    public TestMixingAnswerResponseDTO mapToResponseDTO(TestMixingAnswerDTO dto) {
        return TestMixingAnswerResponseDTO.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .isCorrect(dto.getIsCorrect())
                .status(dto.getStatus())
                .testQuestionMixingId(dto.getTestQuestionMixingId())
                .build();
    }
}
