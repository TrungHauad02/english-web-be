package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.TestWritingDTO;
import com.englishweb.english_web_be.dto.request.TestWritingRequestDTO;
import com.englishweb.english_web_be.dto.response.TestWritingResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.stereotype.Component;

@Component
public class TestWritingMapper implements BaseMapper<TestWritingDTO, TestWritingRequestDTO, TestWritingResponseDTO> {

    @Override
    public TestWritingDTO mapToDTO(TestWritingRequestDTO requestDTO) {
        return TestWritingDTO.builder()
                .id(requestDTO.getId())
                .serial(requestDTO.getSerial())
                .content(requestDTO.getContent())
                .status(requestDTO.getStatus())
                .testId(requestDTO.getTestId())
                .build();
    }

    @Override
    public TestWritingResponseDTO mapToResponseDTO(TestWritingDTO dto) {
        return TestWritingResponseDTO.builder()
                .id(dto.getId())
                .serial(dto.getSerial())
                .content(dto.getContent())
                .status(dto.getStatus())
                .testId(dto.getTestId())
                .build();
    }
}
