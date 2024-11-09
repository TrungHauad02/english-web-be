package com.englishweb.english_web_be.controller;


import com.englishweb.english_web_be.dto.TestMixingAnswerDTO;
import com.englishweb.english_web_be.service.TestMixingAnswerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestMixingAnswerController {
    private final TestMixingAnswerService testMixingAnswerService;


    public TestMixingAnswerController(TestMixingAnswerService testMixingAnswerService) {
        this.testMixingAnswerService = testMixingAnswerService;
    }


    @GetMapping("/api/testmixinganswer/{id}")
    public ResponseEntity<TestMixingAnswerDTO> retrieveAnswerById(@PathVariable String id) {
        return new ResponseEntity<>(testMixingAnswerService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/testmixinganswer")
    public ResponseEntity<TestMixingAnswerDTO> createAnswer(@Valid @RequestBody TestMixingAnswerDTO dto) {
        return new ResponseEntity<>(testMixingAnswerService.create(dto), HttpStatus.CREATED);
    }


    @PutMapping("/api/testmixinganswer/{id}")
    public ResponseEntity<TestMixingAnswerDTO> updateAnswer(@Valid @RequestBody TestMixingAnswerDTO dto, @PathVariable String id) {
        return ResponseEntity.ok(testMixingAnswerService.update(dto, id));
    }

    @DeleteMapping("/api/testmixinganswer/{id}")
    public ResponseEntity<Void> deleteAnswerById(@PathVariable String id) {
        testMixingAnswerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}