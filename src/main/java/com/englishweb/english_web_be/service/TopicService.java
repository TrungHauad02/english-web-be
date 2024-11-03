package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.request.TopicRequestDTO;
import com.englishweb.english_web_be.dto.response.GrammarResponseDTO;
import com.englishweb.english_web_be.dto.response.TopicResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.domain.Page;

public interface TopicService extends BaseService<TopicRequestDTO, TopicResponseDTO> {

    Page<TopicResponseDTO> findTopicWithStatusAndPagingAndSorting(
            StatusEnum status, int page, int size, String sortBy, String sortDir,
            Class<TopicResponseDTO> dtoClass);
}
