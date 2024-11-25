package com.englishweb.english_web_be.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreSpeechTextRequest {
    private String speechText;
    private String realText;
}
