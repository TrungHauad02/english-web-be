package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TestSpeakingDTO;
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
    public ResponseEntity<TestSpeakingDTO> retrieveSpeakingById(@PathVariable String id) {
        return new ResponseEntity<>(testSpeakingService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/testspeaking")
    public ResponseEntity<TestSpeakingDTO> createSpeaking(@Valid @RequestBody TestSpeakingDTO dto) {
        TestSpeakingDTO createdSpeaking = testSpeakingService.create(dto);
        return new ResponseEntity<>(createdSpeaking, HttpStatus.CREATED);
    }

    @PutMapping("/api/testspeaking")
    public ResponseEntity<TestSpeakingDTO> updateSpeaking(@Valid @RequestBody TestSpeakingDTO dto) {
        TestSpeakingDTO updatedSpeaking = testSpeakingService.update(dto);
        return ResponseEntity.ok(updatedSpeaking);
    }

    @DeleteMapping("/api/testspeaking/{id}")
    public ResponseEntity<Void> deleteSpeakingById(@PathVariable String id) {
        testSpeakingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
