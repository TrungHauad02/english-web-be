package com.englishweb.english_web_be.dto.response;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrammarAnswerResponseDTO {
    private String id;
    private String content;
    private Boolean correct;
    private StatusEnum status;
    private String questionId;
}
