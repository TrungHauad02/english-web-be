package com.englishweb.english_web_be.controller;


import com.englishweb.english_web_be.dto.TestMixingQuestionDTO;
import com.englishweb.english_web_be.dto.request.TestMixingQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.TestMixingQuestionResponseDTO;
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
    public ResponseEntity<TestMixingQuestionResponseDTO> retrieveQuestionById(@PathVariable String id) {
        return new ResponseEntity<>(testMixingQuestionService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/testmixingquestion")
    public ResponseEntity<TestMixingQuestionResponseDTO> createQuestion(@Valid @RequestBody TestMixingQuestionRequestDTO dto) {
        return new ResponseEntity<>(testMixingQuestionService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/api/testmixingquestion/{id}")
    public ResponseEntity<TestMixingQuestionResponseDTO> updateQuestion(@Valid @RequestBody TestMixingQuestionRequestDTO dto, @PathVariable String id) {
        return ResponseEntity.ok(testMixingQuestionService.update(dto, id));
    }

    @DeleteMapping("/api/testmixingquestion/{id}")
    public ResponseEntity<Void> deleteQuestionById(@PathVariable String id) {
        testMixingQuestionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
