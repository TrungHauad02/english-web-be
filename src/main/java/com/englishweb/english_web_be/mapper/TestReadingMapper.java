package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.TestReadingDTO;
import com.englishweb.english_web_be.dto.request.TestReadingRequestDTO;
import com.englishweb.english_web_be.dto.response.TestReadingResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TestReadingMapper implements BaseMapper<TestReadingDTO, TestReadingRequestDTO, TestReadingResponseDTO> {

    private final TestReadingQuestionMapper questionMapper;

    public TestReadingMapper(TestReadingQuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    @Override
    public TestReadingDTO mapToDTO(TestReadingRequestDTO requestDTO) {
        return TestReadingDTO.builder()
                .id(requestDTO.getId())
                .serial(requestDTO.getSerial())
                .content(requestDTO.getContent())
                .image(requestDTO.getImage())
                .status(requestDTO.getStatus())
                .testId(requestDTO.getTestId())
                .questions(requestDTO.getQuestions().stream()
                        .map(questionMapper::mapToDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public TestReadingResponseDTO mapToResponseDTO(TestReadingDTO dto) {
        return TestReadingResponseDTO.builder()
                .id(dto.getId())
                .serial(dto.getSerial())
                .content(dto.getContent())
                .image(dto.getImage())
                .status(dto.getStatus())
                .testId(dto.getTestId())
                .questions(dto.getQuestions().stream()
                        .map(questionMapper::mapToResponseDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
