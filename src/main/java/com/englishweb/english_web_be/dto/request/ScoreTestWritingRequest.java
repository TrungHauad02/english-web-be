package com.englishweb.english_web_be.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScoreTestWritingRequest {
    @NotBlank(message = "Text cannot be empty")
    private String text;
    @NotBlank(message = "Topic cannot be empty")
    private String topic;
    @NotBlank(message = "maxScore cannot be empty")
    private String maxScore ;
}
