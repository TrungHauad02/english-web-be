package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.request.CommentListeningQuestionRequest;
import com.englishweb.english_web_be.dto.response.CommentResponse;
import com.englishweb.english_web_be.service.CommentTestListeningQuestionService;
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
@RequestMapping("/api/comment-listening-questions")
@Tag(name = "Comment Listening Question Controller")
@Slf4j
@AllArgsConstructor
public class CommentTestListeningQuestionController {
    private final CommentTestListeningQuestionService service;

    @Operation(method = "POST", summary = "Comment on a listening question",
            description = "Send question content, listening transcript, answers, and user answer to generate a comment")
    @PostMapping
    public ResponseEntity<CommentResponse> commentTestListeningQuestion(@RequestBody @Valid CommentListeningQuestionRequest request) {
        CommentResponse response = service.commentTestListeningQuestion(request.getQuestionContent(), request.getListeningTranscript(), request.getAnswers(), request.getUserAnswer());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
