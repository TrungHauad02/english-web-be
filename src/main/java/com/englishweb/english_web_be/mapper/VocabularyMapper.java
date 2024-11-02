package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.VocabularyDTO;
import com.englishweb.english_web_be.dto.request.VocabularyRequestDTO;
import com.englishweb.english_web_be.dto.response.VocabularyResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class VocabularyMapper implements BaseMapper<VocabularyDTO, VocabularyRequestDTO, VocabularyResponseDTO> {

    @Override
    public VocabularyDTO mapToDTO(VocabularyRequestDTO requestDTO) {
        return null;
    }

    @Override
    public VocabularyResponseDTO mapToResponseDTO(VocabularyDTO dto) {
        return null;
    }
}
