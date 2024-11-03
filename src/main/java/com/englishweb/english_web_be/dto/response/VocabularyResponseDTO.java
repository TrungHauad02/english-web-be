package com.englishweb.english_web_be.dto.response;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.modelenum.WordTypeEnum;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VocabularyResponseDTO implements BaseDTO {
    String id;
    String example;
    String image;
    String word;
    String phonetic;
    String meaning;
    WordTypeEnum wordType;
    StatusEnum status;
    String topicId;
}
