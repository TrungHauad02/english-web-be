package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.VocabularyDTO;
import com.englishweb.english_web_be.dto.request.VocabularyRequestDTO;
import com.englishweb.english_web_be.dto.response.VocabularyResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class VocabularyMapper implements BaseMapper<VocabularyDTO, VocabularyRequestDTO, VocabularyResponseDTO> {

    @Override
    public VocabularyDTO mapToDTO(VocabularyRequestDTO requestDTO) {
        return VocabularyDTO.builder()
                .id(requestDTO.getId())
                .example(requestDTO.getExample())
                .image(requestDTO.getImage())
                .word(requestDTO.getWord())
                .phonetic(requestDTO.getPhonetic())
                .meaning(requestDTO.getMeaning())
                .wordType(requestDTO.getWordType())
                .status(requestDTO.getStatus())
                .topicId(requestDTO.getTopicId())
                .build();
    }

    @Override
    public VocabularyResponseDTO mapToResponseDTO(VocabularyDTO dto) {
        return VocabularyResponseDTO.builder()
                .id(dto.getId())
                .example(dto.getExample())
                .image(dto.getImage())
                .word(dto.getWord())
                .phonetic(dto.getPhonetic())
                .meaning(dto.getMeaning())
                .wordType(dto.getWordType())
                .status(dto.getStatus())
                .topicId(dto.getTopicId())
                .build();
    }
}
