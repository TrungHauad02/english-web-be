package com.englishweb.english_web_be.controller;


import com.englishweb.english_web_be.dto.TestMixingQuestionDTO;
import com.englishweb.english_web_be.service.TestMixingQuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestMixingQuestionController {
    private final TestMixingQuestionService testMixingQuestionService;

    public TestMixingQuestionController(TestMixingQuestionService testMixingQuestionService) {
        this.testMixingQuestionService = testMixingQuestionService;
    }

    @GetMapping("/api/testmixingquestion/{id}")
    public ResponseEntity<TestMixingQuestionDTO> retrieveQuestionById(@PathVariable String id) {
        return new ResponseEntity<>(testMixingQuestionService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/testmixingquestion")
    public ResponseEntity<TestMixingQuestionDTO> createQuestion(@Valid @RequestBody TestMixingQuestionDTO dto) {
        TestMixingQuestionDTO createdQuestion = testMixingQuestionService.create(dto);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    @PutMapping("/api/testmixingquestion")
    public ResponseEntity<TestMixingQuestionDTO> updateQuestion(@Valid @RequestBody TestMixingQuestionDTO dto) {
        TestMixingQuestionDTO updatedQuestion = testMixingQuestionService.update(dto);
        return ResponseEntity.ok(updatedQuestion);
    }

    @DeleteMapping("/api/testmixingquestion/{id}")
    public ResponseEntity<Void> deleteQuestionById(@PathVariable String id) {
        testMixingQuestionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
