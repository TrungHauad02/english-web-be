package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TestListeningAnswerDTO;
import com.englishweb.english_web_be.service.TestListeningAnswerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestListeningAnswerController {
    private final TestListeningAnswerService testListeningAnswerService;

    public TestListeningAnswerController(TestListeningAnswerService testListeningAnswerService) {
        this.testListeningAnswerService = testListeningAnswerService;
    }

    @GetMapping("/api/testlisteninganswer/{id}")
    public ResponseEntity<TestListeningAnswerDTO> retrieveAnswerById(@PathVariable String id) {
        return new ResponseEntity<>(testListeningAnswerService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/testlisteninganswer")
    public ResponseEntity<TestListeningAnswerDTO> createAnswer(@Valid @RequestBody TestListeningAnswerDTO dto) {
        return new ResponseEntity<>(testListeningAnswerService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/api/testlisteninganswer/{id}")
    public ResponseEntity<TestListeningAnswerDTO> updateAnswer(@Valid @RequestBody TestListeningAnswerDTO dto, @PathVariable String id) {
        return ResponseEntity.ok(testListeningAnswerService.update(dto, id));
    }

    @DeleteMapping("/api/testlisteninganswer/{id}")
    public ResponseEntity<Void> deleteAnswerById(@PathVariable String id) {
        testListeningAnswerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
