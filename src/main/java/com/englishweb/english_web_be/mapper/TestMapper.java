package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.TestDTO;
import com.englishweb.english_web_be.dto.request.TestRequestDTO;
import com.englishweb.english_web_be.dto.response.TestResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.TestTypeEnum;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TestMapper implements BaseMapper<TestDTO, TestRequestDTO, TestResponseDTO> {

    private final TestListeningMapper listeningMapper;
    private final TestReadingMapper readingMapper;
    private final TestWritingMapper writingMapper;
    private final TestSpeakingMapper speakingMapper;
    private final TestMixingQuestionMapper mixingQuestionMapper;

    public TestMapper(TestListeningMapper listeningMapper, TestReadingMapper readingMapper,
                      TestWritingMapper writingMapper, TestSpeakingMapper speakingMapper,
                      TestMixingQuestionMapper mixingQuestionMapper) {
        this.listeningMapper = listeningMapper;
        this.readingMapper = readingMapper;
        this.writingMapper = writingMapper;
        this.speakingMapper = speakingMapper;
        this.mixingQuestionMapper = mixingQuestionMapper;
    }

    @Override
    public TestDTO mapToDTO(TestRequestDTO requestDTO) {
        return TestDTO.builder()
                .id(requestDTO.getId())
                .title(requestDTO.getTitle())
                .serial(requestDTO.getSerial())
                .duration(requestDTO.getDuration())
                .type(requestDTO.getType())
                .status(requestDTO.getStatus())
                .testListenings(requestDTO.getTestListenings().stream()
                        .map(listeningMapper::mapToDTO)
                        .collect(Collectors.toList()))
                .testReadings(requestDTO.getTestReadings().stream()
                        .map(readingMapper::mapToDTO)
                        .collect(Collectors.toList()))
                .testWritings(requestDTO.getTestWritings().stream()
                        .map(writingMapper::mapToDTO)
                        .collect(Collectors.toList()))
                .testSpeakings(requestDTO.getTestSpeakings().stream()
                        .map(speakingMapper::mapToDTO)
                        .collect(Collectors.toList()))
                .testMixingQuestions(requestDTO.getTestMixingQuestions().stream()
                        .map(mixingQuestionMapper::mapToDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public TestResponseDTO mapToResponseDTO(TestDTO dto) {
        return TestResponseDTO.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .serial(dto.getSerial())
                .duration(dto.getDuration())
                .type(dto.getType())
                .status(dto.getStatus())
                .testListenings(dto.getTestListenings().stream()
                        .map(listeningMapper::mapToResponseDTO)
                        .collect(Collectors.toList()))
                .testReadings(dto.getTestReadings().stream()
                        .map(readingMapper::mapToResponseDTO)
                        .collect(Collectors.toList()))
                .testWritings(dto.getTestWritings().stream()
                        .map(writingMapper::mapToResponseDTO)
                        .collect(Collectors.toList()))
                .testSpeakings(dto.getTestSpeakings().stream()
                        .map(speakingMapper::mapToResponseDTO)
                        .collect(Collectors.toList()))
                .testMixingQuestions(dto.getTestMixingQuestions().stream()
                        .map(mixingQuestionMapper::mapToResponseDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
