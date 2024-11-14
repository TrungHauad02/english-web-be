package com.englishweb.english_web_be.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoreResponse {
    private Integer score;
    private String comment;
}
