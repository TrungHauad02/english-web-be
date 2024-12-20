package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.request.ScoreSpeechTextRequest;
import com.englishweb.english_web_be.dto.response.ScoreSpeechTextResponse;
import com.englishweb.english_web_be.service.ScoreSpeechText;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/score-speech-text")
@AllArgsConstructor
@Tag(name = "Score Speech Controller")
public class ScoreSpeechTextController {
    ScoreSpeechText service;

    @PostMapping
    public ResponseEntity<ScoreSpeechTextResponse> scoreSpeechText(@RequestBody ScoreSpeechTextRequest request) {
        try {
            return new ResponseEntity<>(service.scoreSpeechTextAndRealText(request.getSpeechText(), request.getRealText()), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
