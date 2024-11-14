package com.englishweb.english_web_be.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpenAIResponseDTO {
    private String id;
    private String object;
    private List<Choice> choices;
    private Usage usage;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Choice {
        private String text;
        private int index;
        private String finishReason;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Usage {
        private int promptTokens;
        private int completionTokens;
        private int totalTokens;
    }
}
