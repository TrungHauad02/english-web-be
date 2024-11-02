package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.TestSpeakingQuestionDTO;
import com.englishweb.english_web_be.dto.request.TestSpeakingQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.TestSpeakingQuestionResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.stereotype.Component;

@Component
public class TestSpeakingQuestionMapper implements BaseMapper<TestSpeakingQuestionDTO, TestSpeakingQuestionRequestDTO, TestSpeakingQuestionResponseDTO> {

    @Override
    public TestSpeakingQuestionDTO mapToDTO(TestSpeakingQuestionRequestDTO requestDTO) {
        return TestSpeakingQuestionDTO.builder()
                .id(requestDTO.getId())
                .content(requestDTO.getContent())
                .serial(requestDTO.getSerial())
                .status(requestDTO.getStatus())
                .testSpeakingId(requestDTO.getTestSpeakingId())
                .build();
    }

    @Override
    public TestSpeakingQuestionResponseDTO mapToResponseDTO(TestSpeakingQuestionDTO dto) {
        return TestSpeakingQuestionResponseDTO.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .serial(dto.getSerial())
                .status(dto.getStatus())
                .testSpeakingId(dto.getTestSpeakingId())
                .build();
    }
}
