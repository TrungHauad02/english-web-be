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
    GrammarService grammarService;
    GrammarAnswerService grammarAnswerService;

    @Override
    public GrammarQuestionDTO mapToDTO(GrammarQuestionRequestDTO requestDTO) {
        return null;
    }

    @Override
    public GrammarQuestionResponseDTO mapToResponseDTO(GrammarQuestionDTO dto) {
        return null;
    }
}
