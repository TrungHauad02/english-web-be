package com.englishweb.english_web_be.dto.response;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListeningAnswerResponseDTO implements BaseDTO {
    String id;
    String content;
    boolean correct;
    StatusEnum status;
    String questionId;
}
