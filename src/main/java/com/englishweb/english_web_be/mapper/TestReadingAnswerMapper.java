package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.TestReadingAnswerDTO;
import com.englishweb.english_web_be.dto.request.TestReadingAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.TestReadingAnswerResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.stereotype.Component;

@Component
public class TestReadingAnswerMapper implements BaseMapper<TestReadingAnswerDTO, TestReadingAnswerRequestDTO, TestReadingAnswerResponseDTO> {

    @Override
    public TestReadingAnswerDTO mapToDTO(TestReadingAnswerRequestDTO requestDTO) {
        return TestReadingAnswerDTO.builder()
                .id(requestDTO.getId())
                .content(requestDTO.getContent())
                .isCorrect(requestDTO.getIsCorrect())
                .status(requestDTO.getStatus())
                .testQuestionReadingId(requestDTO.getTestQuestionReadingId())
                .build();
    }

    @Override
    public TestReadingAnswerResponseDTO mapToResponseDTO(TestReadingAnswerDTO dto) {
        return TestReadingAnswerResponseDTO.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .isCorrect(dto.getIsCorrect())
                .status(dto.getStatus())
                .testQuestionReadingId(dto.getTestQuestionReadingId())
                .build();
    }
}
