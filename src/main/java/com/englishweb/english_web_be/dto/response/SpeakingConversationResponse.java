package com.englishweb.english_web_be.dto.response;

import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpeakingConversationResponse {
    String id;
    String name;
    int serial;
    String content;
    StatusEnum status;
    String speakingId;
    String audioUrl;
}
