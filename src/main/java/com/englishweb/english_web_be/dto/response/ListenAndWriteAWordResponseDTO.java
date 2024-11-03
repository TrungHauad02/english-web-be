package com.englishweb.english_web_be.dto.response;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListenAndWriteAWordResponseDTO implements BaseDTO {
    String id;
    int serial;
    String audioUrl;
    String sentence;
    int missingIndex;
    String correctAnswer;
    StatusEnum status;
    String listeningId;
}
