package com.englishweb.english_web_be.controller;


import com.englishweb.english_web_be.dto.TestMixingAnswerDTO;
import com.englishweb.english_web_be.service.TestMixingAnswerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestMixingAnswercontroller {
    private final TestMixingAnswerService testMixingAnswerService;


    public TestMixingAnswercontroller(TestMixingAnswerService testMixingAnswerService) {
        this.testMixingAnswerService = testMixingAnswerService;
    }


    @GetMapping("/api/testmixinganswer/{id}")
    public ResponseEntity<TestMixingAnswerDTO> retrieveAnswerById(@PathVariable String id) {
        return new ResponseEntity<>(testMixingAnswerService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/testmixinganswer")
    public ResponseEntity<TestMixingAnswerDTO> createAnswer(@Valid @RequestBody TestMixingAnswerDTO dto) {
        TestMixingAnswerDTO createdAnswer = testMixingAnswerService.create(dto);
        return new ResponseEntity<>(createdAnswer, HttpStatus.CREATED);
    }


    @PutMapping("/api/testmixinganswer")
    public ResponseEntity<TestMixingAnswerDTO> updateAnswer(@Valid @RequestBody TestMixingAnswerDTO dto) {
        TestMixingAnswerDTO updatedAnswer = testMixingAnswerService.update(dto);
        return ResponseEntity.ok(updatedAnswer);
    }

    @DeleteMapping("/api/testmixinganswer/{id}")
    public ResponseEntity<Void> deleteAnswerById(@PathVariable String id) {
        testMixingAnswerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
