package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.request.TopicAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.TopicAnswerResponseDTO;
import com.englishweb.english_web_be.service.TopicAnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topics-answer")
@Slf4j
@Tag(name = "Topic Answer Controller")
public class TopicAnswerController {
    private final TopicAnswerService service;

    public TopicAnswerController(TopicAnswerService service) {
        this.service = service;
    }

    @Operation(method = "POST", summary = "Create new topic answer",
            description = "Send a request via this API to create a new topic answer")
    @PostMapping
    public ResponseEntity<TopicAnswerResponseDTO> create(@Valid @RequestBody TopicAnswerRequestDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update topic answer",
            description = "Send a request via this API to update an existing topic answer")
    @PutMapping("/{id}")
    public ResponseEntity<TopicAnswerResponseDTO> update(@Valid @RequestBody TopicAnswerRequestDTO dto, @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete topic answer",
            description = "Send a request via this API to delete an existing topic answer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
