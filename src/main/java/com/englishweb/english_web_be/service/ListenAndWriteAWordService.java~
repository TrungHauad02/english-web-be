package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ListenAndWriteAWordDTO;
import com.englishweb.english_web_be.dto.request.ListenAndWriteAWordRequestDTO;
import com.englishweb.english_web_be.dto.response.ListenAndWriteAWordResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;

import java.util.List;

public interface ListenAndWriteAWordService extends BaseService<ListenAndWriteAWordRequestDTO, ListenAndWriteAWordResponseDTO> {

    List<ListenAndWriteAWordResponseDTO> findByListeningId(String listeningId);

    List<ListenAndWriteAWordResponseDTO> findByListeningIdAndStatus(String listeningId, StatusEnum status);

    List<ListenAndWriteAWordDTO> findDTOByListeningId(String listeningId);
}
