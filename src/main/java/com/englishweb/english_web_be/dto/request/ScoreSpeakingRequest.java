package com.englishweb.english_web_be.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreSpeakingRequest {
    private String speakingConversationId;
    private int scale;
    private String audioProvided;
}
