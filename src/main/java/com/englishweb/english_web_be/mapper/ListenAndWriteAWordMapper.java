package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.ListenAndWriteAWordDTO;
import com.englishweb.english_web_be.dto.request.ListenAndWriteAWordRequestDTO;
import com.englishweb.english_web_be.dto.response.ListenAndWriteAWordResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ListenAndWriteAWordMapper implements BaseMapper<ListenAndWriteAWordDTO, ListenAndWriteAWordRequestDTO, ListenAndWriteAWordResponseDTO> {

    @Override
    public ListenAndWriteAWordDTO mapToDTO(ListenAndWriteAWordRequestDTO requestDTO) {
        return ListenAndWriteAWordDTO.builder()
                .id(requestDTO.getId())
                .serial(requestDTO.getSerial())
                .audioUrl(requestDTO.getAudioUrl())
                .sentence(requestDTO.getSentence())
                .missingIndex(requestDTO.getMissingIndex())
                .correctAnswer(requestDTO.getCorrectAnswer())
                .status(requestDTO.getStatus())
                .listeningId(requestDTO.getListeningId())
                .build();
    }

    @Override
    public ListenAndWriteAWordResponseDTO mapToResponseDTO(ListenAndWriteAWordDTO dto) {
        return ListenAndWriteAWordResponseDTO.builder()
                .id(dto.getId())
                .serial(dto.getSerial())
                .audioUrl(dto.getAudioUrl())
                .sentence(dto.getSentence())
                .missingIndex(dto.getMissingIndex())
                .correctAnswer(dto.getCorrectAnswer())
                .status(dto.getStatus())
                .listeningId(dto.getListeningId())
                .build();
    }
}
