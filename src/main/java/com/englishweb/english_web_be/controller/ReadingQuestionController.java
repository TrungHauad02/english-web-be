package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.ReadingQuestionDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.service.ReadingQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reading-question")
@Slf4j
@Tag(name = "Reading Question Controller")
public class ReadingQuestionController {
    ReadingQuestionService service;

    public ReadingQuestionController(ReadingQuestionService service) {
        this.service = service;
    }

    @Operation(method = "GET", summary = "Get reading questions by reading id",
            description = "Send a request via this API to get all questions for a specific reading")
    @GetMapping
    public ResponseEntity<List<ReadingQuestionDTO>> findByReadingId(
            @RequestParam String readingId,
            @RequestParam (required = false) StatusEnum status) {
        return new ResponseEntity<>(service.findAllByReadingIdAndStatus(readingId, status), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new reading question",
            description = "Send a request via this API to create reading question")
    @PostMapping
    public ResponseEntity<ReadingQuestionDTO> create(@Valid @RequestBody ReadingQuestionDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update reading question",
            description = "Send a request via this API to update reading question")
    @PutMapping("/{id}")
    public ResponseEntity<ReadingQuestionDTO> update(@Valid @RequestBody ReadingQuestionDTO dto,
                                                             @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete reading question",
            description = "Send a request via this API to delete reading question")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}