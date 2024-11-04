package com.englishweb.english_web_be.mapper;

import com.englishweb.english_web_be.dto.GrammarQuestionDTO;
import com.englishweb.english_web_be.dto.request.GrammarQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.GrammarQuestionResponseDTO;
import com.englishweb.english_web_be.service.GrammarAnswerService;
import com.englishweb.english_web_be.service.GrammarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GrammarQuestionMapper implements BaseMapper<GrammarQuestionDTO, GrammarQuestionRequestDTO, GrammarQuestionResponseDTO> {
    GrammarAnswerService grammarAnswerService;

    @Override
    public GrammarQuestionDTO mapToDTO(GrammarQuestionRequestDTO requestDTO) {
        return GrammarQuestionDTO.builder()
                .id(requestDTO.getId())
                .content(requestDTO.getContent())
                .serial(requestDTO.getSerial())
                .explanation(requestDTO.getExplanation())
                .status(requestDTO.getStatus())
                .grammarId(requestDTO.getGrammarId())
                .build();
    }

    @Override
    public GrammarQuestionResponseDTO mapToResponseDTO(GrammarQuestionDTO dto) {
        return GrammarQuestionResponseDTO.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .serial(dto.getSerial())
                .explanation(dto.getExplanation())
                .status(dto.getStatus())
                .answers(grammarAnswerService.findAllByQuestionId(dto.getId()))
                .grammarId(dto.getGrammarId())
                .build();
    }
}
