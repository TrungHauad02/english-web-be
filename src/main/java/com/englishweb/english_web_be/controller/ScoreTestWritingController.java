package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.request.ScoreTestWritingRequest;
import com.englishweb.english_web_be.dto.request.ScoreWritingRequest;
import com.englishweb.english_web_be.dto.response.ScoreCommentResponse;
import com.englishweb.english_web_be.service.ScoreTestWritingService;
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
@RequestMapping("/api/score-test-writings")
@Tag(name = "Score Writing Controller")
@Slf4j
@AllArgsConstructor
public class ScoreTestWritingController {
    ScoreTestWritingService service;

    @Operation(method = "POST", summary = "Score Test writing",
            description = "Send text and topic to score writing")
    @PostMapping
    public ResponseEntity<ScoreCommentResponse> scoreTestWriting(@RequestBody @Valid ScoreTestWritingRequest request) {
        return new ResponseEntity<>(service.scoreWriting(request.getText(), request.getTopic(), request.getMaxScore()), HttpStatus.OK);
    }
}
