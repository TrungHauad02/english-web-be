package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.GrammarDTO;
import com.englishweb.english_web_be.dto.request.GrammarRequestDTO;
import com.englishweb.english_web_be.dto.response.GrammarResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class GrammarMapper implements BaseMapper<GrammarDTO, GrammarRequestDTO, GrammarResponseDTO> {
    @Override
    public GrammarDTO mapToDTO(GrammarRequestDTO grammarRequestDTO) {
        return GrammarDTO.builder()
                .title(grammarRequestDTO.getTitle())
                .serial(grammarRequestDTO.getSerial())
                .content(grammarRequestDTO.getContent())
                .description(grammarRequestDTO.getDescription())
                .image(grammarRequestDTO.getImage())
                .example(grammarRequestDTO.getExample())
                .file(grammarRequestDTO.getFile())
                .status(grammarRequestDTO.getStatus())
                .build();
    }

    @Override
    public GrammarResponseDTO mapToResponseDTO(GrammarDTO dto) {
        return GrammarResponseDTO.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .serial(dto.getSerial())
                .content(dto.getContent())
                .description(dto.getDescription())
                .image(dto.getImage())
                .example(dto.getExample())
                .file(dto.getFile())
                .status(dto.getStatus())
                .build();
    }
}
