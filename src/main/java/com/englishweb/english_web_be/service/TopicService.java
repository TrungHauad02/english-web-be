package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.TopicDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import org.springframework.data.domain.Page;

public interface TopicService extends BaseService<TopicDTO> {

    Page<TopicDTO> findTopicWithStatusAndPagingAndSorting(
            StatusEnum status, int page, int size, String sortBy, String sortDir,
            Class<TopicDTO> dtoClass);
}
