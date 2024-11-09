package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TestListeningQuestionDTO;
import com.englishweb.english_web_be.service.TestListeningQuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestListeningQuestionController {
    private final TestListeningQuestionService testListeningQuestionService;

    public TestListeningQuestionController(TestListeningQuestionService testListeningQuestionService) {
        this.testListeningQuestionService = testListeningQuestionService;
    }

    @GetMapping("/api/testlisteningquestion/{id}")
    public ResponseEntity<TestListeningQuestionDTO> retrieveQuestionById(@PathVariable String id) {
        return new ResponseEntity<>(testListeningQuestionService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/testlisteningquestion")
    public ResponseEntity<TestListeningQuestionDTO> createQuestion(@Valid @RequestBody TestListeningQuestionDTO dto) {
        TestListeningQuestionDTO createdQuestion = testListeningQuestionService.create(dto);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    @PutMapping("/api/testlisteningquestion/{id}")
    public ResponseEntity<TestListeningQuestionDTO> updateQuestion(@Valid @RequestBody TestListeningQuestionDTO dto, @PathVariable String id) {
        return ResponseEntity.ok(testListeningQuestionService.update(dto, id));
    }

    @DeleteMapping("/api/testlisteningquestion/{id}")
    public ResponseEntity<Void> deleteQuestionById(@PathVariable String id) {
        testListeningQuestionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}