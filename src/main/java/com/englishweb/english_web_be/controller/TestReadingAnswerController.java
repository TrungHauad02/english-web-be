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
        return new ResponseEntity<>(testReadingAnswerService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/api/testreadinganswer/{id}")
    public ResponseEntity<TestReadingAnswerDTO> updateAnswer(@Valid @RequestBody TestReadingAnswerDTO dto, @PathVariable String id) {
        return ResponseEntity.ok(testReadingAnswerService.update(dto, id));
    }

    @DeleteMapping("/api/testreadinganswer/{id}")
    public ResponseEntity<Void> deleteAnswerById(@PathVariable String id) {
        testReadingAnswerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}