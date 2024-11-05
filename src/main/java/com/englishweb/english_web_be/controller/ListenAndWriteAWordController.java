package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.request.ListenAndWriteAWordRequestDTO;
import com.englishweb.english_web_be.dto.response.ListenAndWriteAWordResponseDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.service.ListenAndWriteAWordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listenAndWriteAWord")
@Tag(name = "Listen and Write a Word Controller")
public class ListenAndWriteAWordController {
    private final ListenAndWriteAWordService service;

    public ListenAndWriteAWordController(ListenAndWriteAWordService service) {
        this.service = service;
    }

    @Operation(method = "GET", summary = "Get a Listen and Write a Word by id",
            description = "Send a request via this API to get a Listen and Write a Word by id")
    @GetMapping("/{id}")
    public ResponseEntity<ListenAndWriteAWordResponseDTO> getById(@PathVariable String id) {
        ListenAndWriteAWordResponseDTO dto = service.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(method = "GET", summary = "Get all Listen and Write a Words by Listening id",
            description = "Send a request via this API to get all Listen and Write a Words by Listening id")
    @GetMapping
    public ResponseEntity<List<ListenAndWriteAWordResponseDTO>> getByListeningId(
            @RequestParam String listeningId,
            @RequestParam(required = false) StatusEnum status) {
        return new ResponseEntity<>(service.findByListeningIdAndStatus(listeningId, status), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create a new Listen and Write a Word",
            description = "Send a request via this API to create a new Listen and Write a Word")
    @PostMapping
    public ResponseEntity<ListenAndWriteAWordResponseDTO> create(@Valid @RequestBody ListenAndWriteAWordRequestDTO dto) {
        ListenAndWriteAWordResponseDTO createdDto = service.create(dto);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update a Listen and Write a Word",
            description = "Send a request via this API to update a Listen and Write a Word")
    @PutMapping("/{id}")
    public ResponseEntity<ListenAndWriteAWordResponseDTO> update(@Valid @RequestBody ListenAndWriteAWordRequestDTO dto, @PathVariable String id) {
        ListenAndWriteAWordResponseDTO updatedDto = service.update(dto, id);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete a Listen and Write a Word",
            description = "Send a request via this API to delete a Listen and Write a Word")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
