package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.TestListeningAnswerDTO;
import com.englishweb.english_web_be.dto.request.TestListeningAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.TestListeningAnswerResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.stereotype.Component;

@Component
public class TestListeningAnswerMapper implements BaseMapper<TestListeningAnswerDTO, TestListeningAnswerRequestDTO, TestListeningAnswerResponseDTO> {

    @Override
    public TestListeningAnswerDTO mapToDTO(TestListeningAnswerRequestDTO requestDTO) {
        return TestListeningAnswerDTO.builder()
                .id(requestDTO.getId())
                .content(requestDTO.getContent())
                .isCorrect(requestDTO.getIsCorrect())
                .status(requestDTO.getStatus())
                .testQuestionListeningId(requestDTO.getTestQuestionListeningId())
                .build();
    }

    @Override
    public TestListeningAnswerResponseDTO mapToResponseDTO(TestListeningAnswerDTO dto) {
        return TestListeningAnswerResponseDTO.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .isCorrect(dto.getIsCorrect())
                .status(dto.getStatus())
                .testQuestionListeningId(dto.getTestQuestionListeningId())
                .build();
    }
}
