package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TestWritingDTO;
import com.englishweb.english_web_be.service.TestWritingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestWritingController {

    private final TestWritingService testWritingService;

    public TestWritingController(TestWritingService testWritingService) {
        this.testWritingService = testWritingService;
    }


    @GetMapping("/api/testwriting/{id}")
    public ResponseEntity<TestWritingDTO> retrieveWritingById(@PathVariable String id) {
        return new ResponseEntity<>(testWritingService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/testwriting")
    public ResponseEntity<TestWritingDTO> createWriting(@Valid @RequestBody TestWritingDTO dto) {
        TestWritingDTO createdWriting = testWritingService.create(dto);
        return new ResponseEntity<>(createdWriting, HttpStatus.CREATED);
    }

    @PutMapping("/api/testwriting")
    public ResponseEntity<TestWritingDTO> updateWriting(@Valid @RequestBody TestWritingDTO dto) {
        TestWritingDTO updatedWriting = testWritingService.update(dto);
        return ResponseEntity.ok(updatedWriting);
    }
    @DeleteMapping("/api/testwriting/{id}")
    public ResponseEntity<Void> deleteWritingById(@PathVariable String id) {
        testWritingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
