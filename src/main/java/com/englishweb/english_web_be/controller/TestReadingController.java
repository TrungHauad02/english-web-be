package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TestReadingDTO;
import com.englishweb.english_web_be.service.TestReadingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestReadingController {
    private final TestReadingService testReadingService;

    public TestReadingController(TestReadingService testReadingService) {
        this.testReadingService = testReadingService;
    }

    @GetMapping("/api/testreading/{id}")
    public ResponseEntity<TestReadingDTO> retrieveReadingById(@PathVariable String id) {
        return new ResponseEntity<>(testReadingService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/testreading")
    public ResponseEntity<TestReadingDTO> createReading(@Valid @RequestBody TestReadingDTO dto) {
        TestReadingDTO createdReading = testReadingService.create(dto);
        return new ResponseEntity<>(createdReading, HttpStatus.CREATED);
    }

    @PutMapping("/api/testreading")
    public ResponseEntity<TestReadingDTO> updateReading(@Valid @RequestBody TestReadingDTO dto) {
        TestReadingDTO updatedReading = testReadingService.update(dto);
        return ResponseEntity.ok(updatedReading);
    }

    @DeleteMapping("/api/testreading/{id}")
    public ResponseEntity<Void> deleteReadingById(@PathVariable String id) {
        testReadingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
