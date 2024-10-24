package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TestGrammarQuestionDTO;
import com.englishweb.english_web_be.service.TestGrammarQuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestGrammarQuestionController {
    private final TestGrammarQuestionService testGrammarQuestionService;

    public TestGrammarQuestionController(TestGrammarQuestionService testGrammarQuestionService) {
        this.testGrammarQuestionService = testGrammarQuestionService;
    }

    @GetMapping("/api/testgrammarquestion/{id}")
    public ResponseEntity<TestGrammarQuestionDTO> retrieveQuestionById(@PathVariable String id) {
        return new ResponseEntity<>(testGrammarQuestionService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/testgrammarquestion")
    public ResponseEntity<TestGrammarQuestionDTO> createQuestion(@Valid @RequestBody TestGrammarQuestionDTO dto) {
        TestGrammarQuestionDTO createdQuestion = testGrammarQuestionService.create(dto);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    @PutMapping("/api/testgrammarquestion")
    public ResponseEntity<TestGrammarQuestionDTO> updateQuestion(@Valid @RequestBody TestGrammarQuestionDTO dto) {
        TestGrammarQuestionDTO updatedQuestion = testGrammarQuestionService.update(dto);
        return ResponseEntity.ok(updatedQuestion);
    }

    @DeleteMapping("/api/testgrammarquestion/{id}")
    public ResponseEntity<Void> deleteQuestionById(@PathVariable String id) {
        testGrammarQuestionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
