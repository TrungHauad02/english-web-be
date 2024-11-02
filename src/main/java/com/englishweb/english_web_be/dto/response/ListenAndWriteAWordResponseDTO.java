package com.englishweb.english_web_be.dto.response;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
