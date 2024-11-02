package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TestReadingDTO;
import com.englishweb.english_web_be.dto.request.TestReadingRequestDTO;
import com.englishweb.english_web_be.dto.response.TestReadingResponseDTO;
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
    public ResponseEntity<TestReadingResponseDTO> retrieveReadingById(@PathVariable String id) {
        return new ResponseEntity<>(testReadingService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/testreading")
    public ResponseEntity<TestReadingResponseDTO> createReading(@Valid @RequestBody TestReadingRequestDTO dto) {
        return new ResponseEntity<>(testReadingService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/api/testreading/{id}")
    public ResponseEntity<TestReadingResponseDTO> updateReading(@Valid @RequestBody TestReadingRequestDTO dto, @PathVariable String id) {
        return ResponseEntity.ok( testReadingService.update(dto, id));
    }

    @DeleteMapping("/api/testreading/{id}")
    public ResponseEntity<Void> deleteReadingById(@PathVariable String id) {
        testReadingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
