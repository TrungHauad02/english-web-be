package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TestSpeakingQuestionDTO;
import com.englishweb.english_web_be.service.TestSpeakingQuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestSpeakingQuestionController {
    private final TestSpeakingQuestionService testSpeakingQuestionService;

    public TestSpeakingQuestionController(TestSpeakingQuestionService testSpeakingQuestionService) {
        this.testSpeakingQuestionService = testSpeakingQuestionService;
    }

    @GetMapping("/api/testspeakingquestion/{id}")
    public ResponseEntity<TestSpeakingQuestionDTO> retrieveQuestionById(@PathVariable String id) {
        return new ResponseEntity<>(testSpeakingQuestionService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/testspeakingquestion")
    public ResponseEntity<TestSpeakingQuestionDTO> createQuestion(@Valid @RequestBody TestSpeakingQuestionDTO dto) {
        return new ResponseEntity<>(testSpeakingQuestionService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/api/testspeakingquestion/{id}")
    public ResponseEntity<TestSpeakingQuestionDTO> updateQuestion(@Valid @RequestBody TestSpeakingQuestionDTO dto, @PathVariable String id) {
        return ResponseEntity.ok(testSpeakingQuestionService.update(dto, id));
    }

    @DeleteMapping("/api/testspeakingquestion/{id}")
    public ResponseEntity<Void> deleteQuestionById(@PathVariable String id) {
        testSpeakingQuestionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}