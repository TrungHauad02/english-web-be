package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.VocabularyDTO;
import com.englishweb.english_web_be.dto.request.VocabularyRequestDTO;
import com.englishweb.english_web_be.dto.response.VocabularyResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VocabularyService extends BaseService<VocabularyRequestDTO, VocabularyResponseDTO> {

    Page<VocabularyResponseDTO> findByPageTopicId(int page, int size, String sortBy, String sortDir, Class<VocabularyResponseDTO> dtoClass,String topicId);

    List<VocabularyResponseDTO> findByTopicId(String topicId);

    List<VocabularyDTO> findDTOByTopicId(String topicId);
}
