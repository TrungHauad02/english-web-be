package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.request.ListeningAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.ListeningAnswerResponseDTO;
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
    public ResponseEntity<ListeningAnswerResponseDTO> create(@Valid @RequestBody ListeningAnswerRequestDTO dto) {
        ListeningAnswerResponseDTO createdDto = service.create(dto);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update a Listening Answer",
            description = "Send a request via this API to update a Listening Answer")
    @PutMapping("/{id}")
    public ResponseEntity<ListeningAnswerResponseDTO> update(@Valid @RequestBody ListeningAnswerRequestDTO dto, @PathVariable String id) {
        ListeningAnswerResponseDTO updatedDto = service.update(dto, id);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete a Listening Answer",
            description = "Send a request via this API to delete a Listening Answer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
