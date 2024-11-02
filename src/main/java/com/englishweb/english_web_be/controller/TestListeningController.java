package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TestListeningDTO;
import com.englishweb.english_web_be.dto.request.TestListeningRequestDTO;
import com.englishweb.english_web_be.dto.response.TestListeningResponseDTO;
import com.englishweb.english_web_be.service.TestListeningService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestListeningController {
    private final TestListeningService testListeningService;

    public TestListeningController(TestListeningService testListeningService) {
        this.testListeningService = testListeningService;
    }

    @GetMapping("/api/testlistening/{id}")
    public ResponseEntity<TestListeningResponseDTO> retrieveListeningById(@PathVariable String id) {
        return new ResponseEntity<>(testListeningService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/testlistening")
    public ResponseEntity<TestListeningResponseDTO> createListening(@Valid @RequestBody TestListeningRequestDTO dto) {
        return new ResponseEntity<>(testListeningService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/api/testlistening/{id}")
    public ResponseEntity<TestListeningResponseDTO> updateListening(@Valid @RequestBody TestListeningRequestDTO dto, @PathVariable String id) {
        return ResponseEntity.ok(testListeningService.update(dto, id));
    }

    @DeleteMapping("/api/testlistening/{id}")
    public ResponseEntity<Void> deleteListeningById(@PathVariable String id) {
        testListeningService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
