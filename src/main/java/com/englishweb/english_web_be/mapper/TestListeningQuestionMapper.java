package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.TestListeningQuestionDTO;
import com.englishweb.english_web_be.dto.request.TestListeningQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.TestListeningQuestionResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TestListeningQuestionMapper implements BaseMapper<TestListeningQuestionDTO, TestListeningQuestionRequestDTO, TestListeningQuestionResponseDTO> {

    private final TestListeningAnswerMapper answerMapper;

    public TestListeningQuestionMapper(TestListeningAnswerMapper answerMapper) {
        this.answerMapper = answerMapper;
    }

    @Override
    public TestListeningQuestionDTO mapToDTO(TestListeningQuestionRequestDTO requestDTO) {
        return TestListeningQuestionDTO.builder()
                .id(requestDTO.getId())
                .content(requestDTO.getContent())
                .serial(requestDTO.getSerial())
                .status(requestDTO.getStatus())
                .testListeningId(requestDTO.getTestListeningId())
                .answers(requestDTO.getAnswers().stream()
                        .map(answerMapper::mapToDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public TestListeningQuestionResponseDTO mapToResponseDTO(TestListeningQuestionDTO dto) {
        return TestListeningQuestionResponseDTO.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .serial(dto.getSerial())
                .status(dto.getStatus())
                .testListeningId(dto.getTestListeningId())
                .answers(dto.getAnswers().stream()
                        .map(answerMapper::mapToResponseDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
