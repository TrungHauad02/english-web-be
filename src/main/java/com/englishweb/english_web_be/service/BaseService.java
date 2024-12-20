package com.englishweb.english_web_be.service;

import org.springframework.data.domain.Page;

public interface BaseService<DTO> {

    Page<DTO> findByPage(int page, int size, String sortBy, String sortDir, Class<DTO> dtoClass);

    DTO findById(String id);

    DTO create(DTO dto);

    DTO update(DTO dto, String id);

    void delete(String id);

    void isExist(String id);
}
