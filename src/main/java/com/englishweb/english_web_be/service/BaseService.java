package com.englishweb.english_web_be.service;

import org.springframework.data.domain.Page;

public interface BaseService<RequestDTO, ResponseDTO> {

    Page<ResponseDTO> findByPage(int page, int size, String sortBy, String sortDir, Class<ResponseDTO> dtoClass);

    ResponseDTO findById(String id);

    ResponseDTO create(RequestDTO dto);

    ResponseDTO update(RequestDTO dto, String id);

    void delete(String id);

    void isExist(String id);
}
