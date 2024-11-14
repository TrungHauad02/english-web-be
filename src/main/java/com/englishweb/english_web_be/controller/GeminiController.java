package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.service.GeminiClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gemini")
@Slf4j
@Tag(name = "Gemini Controller")
@AllArgsConstructor
public class GeminiController {
    GeminiClientService service;

    @PostMapping("/generate-text")
    public String generateText(@RequestBody String prompt) {
        return service.generateText(prompt);
    }
}
