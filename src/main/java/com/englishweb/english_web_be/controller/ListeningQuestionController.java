package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.request.ListeningQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.ListeningQuestionResponseDTO;
import com.englishweb.english_web_be.service.ListeningQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listening-questions")
@Slf4j
@Tag(name = "Listening Question Controller")
public class ListeningQuestionController {
    ListeningQuestionService service;

    public ListeningQuestionController(ListeningQuestionService service) {
        this.service = service;
    }

    @Operation(method = "GET", summary = "Get listening questions by listening id",
            description = "Send a request via this API to get all questions for a specific listening")
    @GetMapping
    public ResponseEntity<List<ListeningQuestionResponseDTO>> findByListeningId(@RequestParam String listeningId) {
        return new ResponseEntity<>(service.findByListeningId(listeningId), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new listening question",
            description = "Send a request via this API to create listening question")
    @PostMapping
    public ResponseEntity<ListeningQuestionResponseDTO> create(@Valid @RequestBody ListeningQuestionRequestDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update listening question",
            description = "Send a request via this API to update listening question")
    @PutMapping("/{id}")
    public ResponseEntity<ListeningQuestionResponseDTO> update(@Valid @RequestBody ListeningQuestionRequestDTO dto,
                                                               @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete listening question",
            description = "Send a request via this API to delete listening question")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}