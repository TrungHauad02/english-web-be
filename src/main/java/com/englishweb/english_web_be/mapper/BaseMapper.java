package com.englishweb.english_web_be.mapper;

public interface BaseMapper <DTO, RequestDTO, ResponseDTO>{
    DTO mapToDTO(RequestDTO requestDTO);

    ResponseDTO mapToResponseDTO(DTO dto);
}
