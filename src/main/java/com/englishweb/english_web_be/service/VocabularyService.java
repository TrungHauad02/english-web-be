package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.VocabularyDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VocabularyService extends BaseService<VocabularyDTO> {

    Page<VocabularyDTO> findByPageTopicId(int page, int size, String sortBy, String sortDir, Class<VocabularyDTO> dtoClass,String topicId);

    Page<VocabularyDTO> findByPageAndStatusAndTopicId(StatusEnum status, int page, int size, String sortBy, String sortDir, Class<VocabularyDTO> dtoClass, String topicId);

    List<VocabularyDTO> findByTopicId(String topicId);
}
