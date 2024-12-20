package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.ListeningAnswerDTO;
import com.englishweb.english_web_be.service.ListeningAnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/listening-answer")
@Tag(name = "Listening Answer Controller")
public class ListeningAnswerController {
    private final ListeningAnswerService service;

    public ListeningAnswerController(ListeningAnswerService service) {
        this.service = service;
    }

    @Operation(method = "POST", summary = "Create a new Listening Answer",
            description = "Send a request via this API to create a new Listening Answer")
    @PostMapping
    public ResponseEntity<ListeningAnswerDTO> create(@Valid @RequestBody ListeningAnswerDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update a Listening Answer",
            description = "Send a request via this API to update a Listening Answer")
    @PutMapping("/{id}")
    public ResponseEntity<ListeningAnswerDTO> update(@Valid @RequestBody ListeningAnswerDTO dto, @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete a Listening Answer",
            description = "Send a request via this API to delete a Listening Answer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
