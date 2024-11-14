package com.englishweb.english_web_be.dto.request;

import com.englishweb.english_web_be.util.OpenAIModel;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpenAIRequestDTO {
    private String model;
    private String prompt;
    private int maxTokens;
    private double temperature;

    public OpenAIRequestDTO(OpenAIModel model, String prompt, int maxTokens, double temperature) {
        this.model = model.getModelName();
        this.prompt = prompt;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
    }

    public void setModel(OpenAIModel model) {
        this.model = model.getModelName();
    }
}
