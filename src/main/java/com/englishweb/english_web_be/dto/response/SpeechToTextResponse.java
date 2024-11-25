package com.englishweb.english_web_be.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpeechToTextResponse {
    private String transcript;
    private Double confidence;
}
