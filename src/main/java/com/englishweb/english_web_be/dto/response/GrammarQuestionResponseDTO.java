package com.englishweb.english_web_be.dto.response;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrammarQuestionResponseDTO implements BaseDTO {
    String id;
    String content;
    int serial;
    String explanation;
    StatusEnum status;
    List<GrammarAnswerResponseDTO> answers;
    String grammarId;
}
