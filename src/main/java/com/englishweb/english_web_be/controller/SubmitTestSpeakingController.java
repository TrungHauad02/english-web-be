package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.SubmitTestSpeakingDTO;
import com.englishweb.english_web_be.service.impl.SubmitTestSpeakingServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submit-test-speakings")
@Slf4j
@Tag(name = "SubmitTestSpeaking Controller")
public class SubmitTestSpeakingController {

    private final SubmitTestSpeakingServiceImpl service;

    public SubmitTestSpeakingController(SubmitTestSpeakingServiceImpl service) {
        this.service = service;
    }

    @Operation(method = "GET", summary = "Get submit test speaking by id", description = "Send a request via this API to get submit test speaking information")
    @GetMapping("/{id}")
    public ResponseEntity<SubmitTestSpeakingDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new submit test speaking", description = "Send a request via this API to create submit test speaking")
    @PostMapping
    public ResponseEntity<SubmitTestSpeakingDTO> create(@Valid @RequestBody SubmitTestSpeakingDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update submit test speaking", description = "Send a request via this API to update submit test speaking")
    @PutMapping("/{id}")
    public ResponseEntity<SubmitTestSpeakingDTO> update(@Valid @RequestBody SubmitTestSpeakingDTO dto, @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete submit test speaking", description = "Send a request via this API to delete submit test speaking")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
