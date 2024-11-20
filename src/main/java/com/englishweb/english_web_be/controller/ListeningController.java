package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.ListeningDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.service.ListeningService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/listenings")
@Slf4j
@Tag(name = "Listening Controller")
public class ListeningController {
    ListeningService service;

    public ListeningController(ListeningService service) {
        this.service = service;
    }

    @Operation(method = "GET", summary = "Get listening with status, sorting and pagination",
            description = "Send a request via this API to get listening with status, sorting and pagination. Status is optional")
    @GetMapping
    public ResponseEntity<Page<ListeningDTO>> findListeningWithStatusAndPagingAndSorting(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) StatusEnum status) {
        return new ResponseEntity<>(
                service.findWithPagingSortingSearching(title, status ,page, size, sortBy, sortDir, ListeningDTO.class),
                HttpStatus.OK);
    }

    @Operation(method = "GET", summary = "Get listening by id",
            description = "Send a request via this API to get listening information")
    @GetMapping("/{id}")
    public ResponseEntity<ListeningDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new listening",
            description = "Send a request via this API to create listening")
    @PostMapping
    public ResponseEntity<ListeningDTO> create(@Valid @RequestBody ListeningDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update listening",
            description = "Send a request via this API to update listening")
    @PutMapping("/{id}")
    public ResponseEntity<ListeningDTO> update(@Valid @RequestBody ListeningDTO dto, @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete listening",
            description = "Send a request via this API to delete listening")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}