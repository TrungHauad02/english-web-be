package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.request.ScoreWritingRequest;
import com.englishweb.english_web_be.dto.response.ScoreWritingResponse;
import com.englishweb.english_web_be.service.ScoreWritingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/score-writings")
@Tag(name = "Score Writing Controller")
@Slf4j
@AllArgsConstructor
public class ScoreWritingController {
    ScoreWritingService service;

    @Operation(method = "POST", summary = "Score writing",
                description = "Send text and topic to score writing")
    @PostMapping
    public ResponseEntity<ScoreWritingResponse> scoreWriting(@RequestBody @Valid ScoreWritingRequest request) {
        return new ResponseEntity<>(service.scoreWriting(request.getText(), request.getTopic()), HttpStatus.OK);
    }
}
