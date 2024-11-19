package com.englishweb.english_web_be.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoreWritingResponse {
    private String score;
    private String comment;
}
