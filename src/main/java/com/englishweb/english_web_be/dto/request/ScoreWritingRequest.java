package com.englishweb.english_web_be.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScoreWritingRequest {
    @NotBlank(message = "Text cannot be empty")
    private String text;
    @NotBlank(message = "Topic cannot be empty")
    private String topic;
}
