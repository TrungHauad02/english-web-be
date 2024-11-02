package com.englishweb.english_web_be.controller;


import com.englishweb.english_web_be.dto.TestMixingAnswerDTO;
import com.englishweb.english_web_be.dto.request.TestMixingAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.TestMixingAnswerResponseDTO;
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
    public ResponseEntity<TestMixingAnswerResponseDTO> retrieveAnswerById(@PathVariable String id) {
        return new ResponseEntity<>(testMixingAnswerService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/testmixinganswer")
    public ResponseEntity<TestMixingAnswerResponseDTO> createAnswer(@Valid @RequestBody TestMixingAnswerRequestDTO dto) {
        return new ResponseEntity<>(testMixingAnswerService.create(dto), HttpStatus.CREATED);
    }


    @PutMapping("/api/testmixinganswer/{id}")
    public ResponseEntity<TestMixingAnswerResponseDTO> updateAnswer(@Valid @RequestBody TestMixingAnswerRequestDTO dto, @PathVariable String id) {
        return ResponseEntity.ok(testMixingAnswerService.update(dto, id));
    }

    @DeleteMapping("/api/testmixinganswer/{id}")
    public ResponseEntity<Void> deleteAnswerById(@PathVariable String id) {
        testMixingAnswerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
