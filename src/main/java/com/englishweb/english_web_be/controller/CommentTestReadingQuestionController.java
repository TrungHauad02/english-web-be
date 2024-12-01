package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.request.CommentReadingQuestionRequest;
import com.englishweb.english_web_be.dto.response.CommentResponse;
import com.englishweb.english_web_be.service.CommentTestReadingQuestionService;
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
@RequestMapping("/api/comment-reading-questions")
@Tag(name = "Comment Reading Question Controller")
@Slf4j
@AllArgsConstructor
public class CommentTestReadingQuestionController {
    private final CommentTestReadingQuestionService service;

    @Operation(method = "POST", summary = "Comment on a reading question",
            description = "Send question content, reading content, answers, and user answer to generate a comment")
    @PostMapping
    public ResponseEntity<CommentResponse> commentTestReadingQuestion(@RequestBody @Valid CommentReadingQuestionRequest request) {
        CommentResponse response = service.commentTestReadingQuestion(request.getQuestionContent(), request.getReadingContent(), request.getAnswers(), request.getUserAnswer());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
