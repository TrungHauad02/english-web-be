package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.request.PostRequest;
import com.englishweb.english_web_be.dto.response.PostResponse;
import com.englishweb.english_web_be.service.ScoreSpeakingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/score-speakings")
@AllArgsConstructor
@Tag(name = "Score Speaking Controller")
public class ScoreSpeakingController {
    ScoreSpeakingService service;

    @PostMapping("/post")
    public ResponseEntity<PostResponse> addPost(@RequestBody PostRequest request){
        return new ResponseEntity<>(service.addPost(request), HttpStatus.OK);
    }
}
