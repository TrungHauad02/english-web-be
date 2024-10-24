package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TestReadingQuestionDTO;
import com.englishweb.english_web_be.service.TestReadingQuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestReadingQuestionController {
    private final TestReadingQuestionService testReadingQuestionService;

    public TestReadingQuestionController(TestReadingQuestionService testReadingQuestionService) {
        this.testReadingQuestionService = testReadingQuestionService;
    }

    @GetMapping("/api/testreadingquestion/{id}")
    public ResponseEntity<TestReadingQuestionDTO> retrieveQuestionById(@PathVariable String id) {
        return new ResponseEntity<>(testReadingQuestionService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/testreadingquestion")
    public ResponseEntity<TestReadingQuestionDTO> createQuestion(@Valid @RequestBody TestReadingQuestionDTO dto) {
        TestReadingQuestionDTO createdQuestion = testReadingQuestionService.create(dto);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    @PutMapping("/api/testreadingquestion")
    public ResponseEntity<TestReadingQuestionDTO> updateQuestion(@Valid @RequestBody TestReadingQuestionDTO dto) {
        TestReadingQuestionDTO updatedQuestion = testReadingQuestionService.update(dto);
        return ResponseEntity.ok(updatedQuestion);
    }

    @DeleteMapping("/api/testreadingquestion/{id}")
    public ResponseEntity<Void> deleteQuestionById(@PathVariable String id) {
        testReadingQuestionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
