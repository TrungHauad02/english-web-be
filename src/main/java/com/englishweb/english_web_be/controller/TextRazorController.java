package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.service.TextRazorService;
import com.textrazor.AnalysisException;
import com.textrazor.NetworkException;
import com.textrazor.annotations.AnalyzedText;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Tag(name = "Text Razor Controller")
@RequestMapping("/api/razor")
public class TextRazorController {
    TextRazorService service;

    public TextRazorController(TextRazorService service) {
        this.service = service;
    }

    @Operation(method = "POST", summary = "Analyze text",
                description = "Send a request via this API to analyze text")
    @PostMapping
    public ResponseEntity<AnalyzedText> analyzeText(@RequestBody String text) {
        try {
            return new ResponseEntity<>(service.analyzeText(text), HttpStatus.OK);
        } catch (AnalysisException | NetworkException e) {
            throw new RuntimeException(e);
        }
    }
}
