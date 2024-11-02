package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ListenAndWriteAWordDTO;
import com.englishweb.english_web_be.dto.request.ListenAndWriteAWordRequestDTO;
import com.englishweb.english_web_be.dto.response.ListenAndWriteAWordResponseDTO;

import java.util.List;

public interface ListenAndWriteAWordService extends BaseService<ListenAndWriteAWordRequestDTO, ListenAndWriteAWordResponseDTO> {

    List<ListenAndWriteAWordResponseDTO> findByListeningId(String listeningId);

    List<ListenAndWriteAWordDTO> findDTOByListeningId(String listeningId);
}
