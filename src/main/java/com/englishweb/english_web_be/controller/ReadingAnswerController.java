package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.request.ReadingAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.ReadingAnswerResponseDTO;
import com.englishweb.english_web_be.service.ReadingAnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reading-answer")
@Slf4j
@Tag(name = "Reading Answer Controller")
public class ReadingAnswerController {
    ReadingAnswerService service;

    public ReadingAnswerController(ReadingAnswerService service) {
        this.service = service;
    }

    @Operation(method = "POST", summary = "Create new reading answer",
            description = "Send a request via this API to create reading answer")
    @PostMapping
    public ResponseEntity<ReadingAnswerResponseDTO> create(@Valid @RequestBody ReadingAnswerRequestDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update reading answer",
            description = "Send a request via this API to update reading answer")
    @PutMapping("/{id}")
    public ResponseEntity<ReadingAnswerResponseDTO> update(@Valid @RequestBody ReadingAnswerRequestDTO dto,
                                                           @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete reading answer",
            description = "Send a request via this API to delete reading answer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}