package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.request.CommentMixingQuestionRequest;
import com.englishweb.english_web_be.dto.response.CommentResponse;
import com.englishweb.english_web_be.service.CommentTestMixingQuestionService;
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
@RequestMapping("/api/comment-mixing-questions")
@Tag(name = "Comment Mixing Question Controller")
@Slf4j
@AllArgsConstructor
public class CommentTestMixingQuestionController {
    private final CommentTestMixingQuestionService service;

    @Operation(method = "POST", summary = "Comment on a mixing question",
            description = "Send question content, answers, and user answer to generate a comment")
    @PostMapping
    public ResponseEntity<CommentResponse> commentTestMixingQuestion(@RequestBody @Valid CommentMixingQuestionRequest request) {
        CommentResponse response = service.commentTestMixingQuestion(request.getQuestionContent(), request.getAnswers(), request.getUserAnswer());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
