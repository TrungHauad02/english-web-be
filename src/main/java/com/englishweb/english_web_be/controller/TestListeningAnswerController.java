package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TestListeningAnswerDTO;
import com.englishweb.english_web_be.dto.request.TestListeningAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.TestListeningAnswerResponseDTO;
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
    public ResponseEntity<TestListeningAnswerResponseDTO> retrieveAnswerById(@PathVariable String id) {
        return new ResponseEntity<>(testListeningAnswerService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/testlisteninganswer")
    public ResponseEntity<TestListeningAnswerResponseDTO> createAnswer(@Valid @RequestBody TestListeningAnswerRequestDTO dto) {
        return new ResponseEntity<>(testListeningAnswerService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/api/testlisteninganswer/{id}")
    public ResponseEntity<TestListeningAnswerResponseDTO> updateAnswer(@Valid @RequestBody TestListeningAnswerRequestDTO dto, @PathVariable String id) {
        return ResponseEntity.ok(testListeningAnswerService.update(dto, id));
    }

    @DeleteMapping("/api/testlisteninganswer/{id}")
    public ResponseEntity<Void> deleteAnswerById(@PathVariable String id) {
        testListeningAnswerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
