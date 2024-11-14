package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.SubmitTestReadingAnswerDTO;
import com.englishweb.english_web_be.service.impl.SubmitTestReadingAnswerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submit-test-reading-answers")
@Slf4j
@Tag(name = "SubmitTestReadingAnswer Controller")
public class SubmitTestReadingAnswerController {

    private final SubmitTestReadingAnswerServiceImpl service;

    public SubmitTestReadingAnswerController(SubmitTestReadingAnswerServiceImpl service) {
        this.service = service;
    }

    @Operation(method = "GET", summary = "Get submit test reading answer by id", description = "Send a request via this API to get submit test reading answer information")
    @GetMapping("/{id}")
    public ResponseEntity<SubmitTestReadingAnswerDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new submit test reading answer", description = "Send a request via this API to create submit test reading answer")
    @PostMapping
    public ResponseEntity<SubmitTestReadingAnswerDTO> create(@Valid @RequestBody SubmitTestReadingAnswerDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update submit test reading answer", description = "Send a request via this API to update submit test reading answer")
    @PutMapping("/{id}")
    public ResponseEntity<SubmitTestReadingAnswerDTO> update(@Valid @RequestBody SubmitTestReadingAnswerDTO dto, @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete submit test reading answer", description = "Send a request via this API to delete submit test reading answer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
