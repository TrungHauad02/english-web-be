package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TestGrammarAnswerDTO;
import com.englishweb.english_web_be.service.TestGrammarAnswerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestGrammarAnswerController {
    private final TestGrammarAnswerService testGrammarAnswerService;


    public TestGrammarAnswerController(TestGrammarAnswerService testGrammarAnswerService) {
        this.testGrammarAnswerService = testGrammarAnswerService;
    }


    @GetMapping("/api/testgrammaranswer/{id}")
    public ResponseEntity<TestGrammarAnswerDTO> retrieveAnswerById(@PathVariable String id) {
        return new ResponseEntity<>(testGrammarAnswerService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/testgrammaranswer")
    public ResponseEntity<TestGrammarAnswerDTO> createAnswer(@Valid @RequestBody TestGrammarAnswerDTO dto) {
        TestGrammarAnswerDTO createdAnswer = testGrammarAnswerService.create(dto);
        return new ResponseEntity<>(createdAnswer, HttpStatus.CREATED);
    }


    @PutMapping("/api/testgrammaranswer")
    public ResponseEntity<TestGrammarAnswerDTO> updateAnswer(@Valid @RequestBody TestGrammarAnswerDTO dto) {
        TestGrammarAnswerDTO updatedAnswer = testGrammarAnswerService.update(dto);
        return ResponseEntity.ok(updatedAnswer);
    }

    @DeleteMapping("/api/testgrammaranswer/{id}")
    public ResponseEntity<Void> deleteAnswerById(@PathVariable String id) {
        testGrammarAnswerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
