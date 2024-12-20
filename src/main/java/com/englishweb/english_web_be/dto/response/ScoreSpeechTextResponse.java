package com.englishweb.english_web_be.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreSpeechTextResponse {
    private String score;
    private List<CommentResponse> comment;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class CommentResponse {
        private Integer startIndex;
        private Integer endIndex;
        private String errorChars;
    }
}
