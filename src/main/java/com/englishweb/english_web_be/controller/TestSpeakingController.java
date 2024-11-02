package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TestSpeakingDTO;
import com.englishweb.english_web_be.dto.request.TestSpeakingRequestDTO;
import com.englishweb.english_web_be.dto.response.TestSpeakingResponseDTO;
import com.englishweb.english_web_be.service.TestSpeakingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestSpeakingController {
    private final TestSpeakingService testSpeakingService;

    public TestSpeakingController(TestSpeakingService testSpeakingService) {
        this.testSpeakingService = testSpeakingService;
    }

    @GetMapping("/api/testspeaking/{id}")
    public ResponseEntity<TestSpeakingResponseDTO> retrieveSpeakingById(@PathVariable String id) {
        return new ResponseEntity<>(testSpeakingService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/testspeaking")
    public ResponseEntity<TestSpeakingResponseDTO> createSpeaking(@Valid @RequestBody TestSpeakingRequestDTO dto) {
        return new ResponseEntity<>(testSpeakingService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/api/testspeaking/{id}")
    public ResponseEntity<TestSpeakingResponseDTO> updateSpeaking(@Valid @RequestBody TestSpeakingRequestDTO dto, @PathVariable String id) {
        return ResponseEntity.ok(testSpeakingService.update(dto, id));
    }

    @DeleteMapping("/api/testspeaking/{id}")
    public ResponseEntity<Void> deleteSpeakingById(@PathVariable String id) {
        testSpeakingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
