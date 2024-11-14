package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.SubmitTestMixingAnswerDTO;
import com.englishweb.english_web_be.service.impl.SubmitTestMixingAnswerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submit-test-mixing-answers")
@Slf4j
@Tag(name = "SubmitTestMixingAnswer Controller")
public class SubmitTestMixingAnswerController {

    private final SubmitTestMixingAnswerServiceImpl service;

    public SubmitTestMixingAnswerController(SubmitTestMixingAnswerServiceImpl service) {
        this.service = service;
    }

    @Operation(method = "GET", summary = "Get submit test mixing answer by id", description = "Send a request via this API to get submit test mixing answer information")
    @GetMapping("/{id}")
    public ResponseEntity<SubmitTestMixingAnswerDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new submit test mixing answer", description = "Send a request via this API to create submit test mixing answer")
    @PostMapping
    public ResponseEntity<SubmitTestMixingAnswerDTO> create(@Valid @RequestBody SubmitTestMixingAnswerDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update submit test mixing answer", description = "Send a request via this API to update submit test mixing answer")
    @PutMapping("/{id}")
    public ResponseEntity<SubmitTestMixingAnswerDTO> update(@Valid @RequestBody SubmitTestMixingAnswerDTO dto, @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete submit test mixing answer", description = "Send a request via this API to delete submit test mixing answer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
