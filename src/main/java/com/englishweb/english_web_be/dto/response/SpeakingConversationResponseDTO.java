package com.englishweb.english_web_be.dto.response;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpeakingConversationResponseDTO implements BaseDTO {
    String id;
    String name;
    int serial;
    String content;
    StatusEnum status;
    String speakingId;
}
