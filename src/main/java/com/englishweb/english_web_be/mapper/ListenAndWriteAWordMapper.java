package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.ListenAndWriteAWordDTO;
import com.englishweb.english_web_be.dto.request.ListenAndWriteAWordRequestDTO;
import com.englishweb.english_web_be.dto.response.ListenAndWriteAWordResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ListenAndWriteAWordMapper implements BaseMapper<ListenAndWriteAWordDTO, ListenAndWriteAWordRequestDTO, ListenAndWriteAWordResponseDTO> {

    @Override
    public ListenAndWriteAWordDTO mapToDTO(ListenAndWriteAWordRequestDTO requestDTO) {
        return null;
    }

    @Override
    public ListenAndWriteAWordResponseDTO mapToResponseDTO(ListenAndWriteAWordDTO dto) {
        return null;
    }
}
