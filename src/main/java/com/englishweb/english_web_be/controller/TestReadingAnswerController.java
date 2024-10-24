package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TestReadingAnswerDTO;
import com.englishweb.english_web_be.service.TestReadingAnswerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestReadingAnswerController {
    private final TestReadingAnswerService testReadingAnswerService;

    public TestReadingAnswerController(TestReadingAnswerService testReadingAnswerService) {
        this.testReadingAnswerService = testReadingAnswerService;
    }

    @GetMapping("/api/testreadinganswer/{id}")
    public ResponseEntity<TestReadingAnswerDTO> retrieveAnswerById(@PathVariable String id) {
        return new ResponseEntity<>(testReadingAnswerService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/testreadinganswer")
    public ResponseEntity<TestReadingAnswerDTO> createAnswer(@Valid @RequestBody TestReadingAnswerDTO dto) {
        TestReadingAnswerDTO createdAnswer = testReadingAnswerService.create(dto);
        return new ResponseEntity<>(createdAnswer, HttpStatus.CREATED);
    }

    @PutMapping("/api/testreadinganswer")
    public ResponseEntity<TestReadingAnswerDTO> updateAnswer(@Valid @RequestBody TestReadingAnswerDTO dto) {
        TestReadingAnswerDTO updatedAnswer = testReadingAnswerService.update(dto);
        return ResponseEntity.ok(updatedAnswer);
    }

    @DeleteMapping("/api/testreadinganswer/{id}")
    public ResponseEntity<Void> deleteAnswerById(@PathVariable String id) {
        testReadingAnswerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
