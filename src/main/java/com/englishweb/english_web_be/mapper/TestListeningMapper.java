package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.TestListeningDTO;
import com.englishweb.english_web_be.dto.request.TestListeningRequestDTO;
import com.englishweb.english_web_be.dto.response.TestListeningResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TestListeningMapper implements BaseMapper<TestListeningDTO, TestListeningRequestDTO, TestListeningResponseDTO> {

    private final TestListeningQuestionMapper questionMapper;

    public TestListeningMapper(TestListeningQuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    @Override
    public TestListeningDTO mapToDTO(TestListeningRequestDTO requestDTO) {
        return TestListeningDTO.builder()
                .id(requestDTO.getId())
                .serial(requestDTO.getSerial())
                .content(requestDTO.getContent())
                .transcript(requestDTO.getTranscript())
                .status(requestDTO.getStatus())
                .testId(requestDTO.getTestId())
                .questions(requestDTO.getQuestions().stream()
                        .map(questionMapper::mapToDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public TestListeningResponseDTO mapToResponseDTO(TestListeningDTO dto) {
        return TestListeningResponseDTO.builder()
                .id(dto.getId())
                .serial(dto.getSerial())
                .content(dto.getContent())
                .transcript(dto.getTranscript())
                .status(dto.getStatus())
                .testId(dto.getTestId())
                .questions(dto.getQuestions().stream()
                        .map(questionMapper::mapToResponseDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
