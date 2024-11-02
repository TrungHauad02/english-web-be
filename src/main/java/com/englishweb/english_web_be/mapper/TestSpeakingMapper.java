package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.TestSpeakingDTO;
import com.englishweb.english_web_be.dto.request.TestSpeakingRequestDTO;
import com.englishweb.english_web_be.dto.response.TestSpeakingResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TestSpeakingMapper implements BaseMapper<TestSpeakingDTO, TestSpeakingRequestDTO, TestSpeakingResponseDTO> {

    private final TestSpeakingQuestionMapper questionMapper;

    public TestSpeakingMapper(TestSpeakingQuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    @Override
    public TestSpeakingDTO mapToDTO(TestSpeakingRequestDTO requestDTO) {
        return TestSpeakingDTO.builder()
                .id(requestDTO.getId())
                .title(requestDTO.getTitle())
                .serial(requestDTO.getSerial())
                .status(requestDTO.getStatus())
                .testId(requestDTO.getTestId())
                .questions(requestDTO.getQuestions().stream()
                        .map(questionMapper::mapToDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public TestSpeakingResponseDTO mapToResponseDTO(TestSpeakingDTO dto) {
        return TestSpeakingResponseDTO.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .serial(dto.getSerial())
                .status(dto.getStatus())
                .testId(dto.getTestId())
                .questions(dto.getQuestions().stream()
                        .map(questionMapper::mapToResponseDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
