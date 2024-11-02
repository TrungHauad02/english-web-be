package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.request.GrammarRequestDTO;
import com.englishweb.english_web_be.dto.response.GrammarResponseDTO;
import com.englishweb.english_web_be.mapper.GrammarMapper;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.englishweb.english_web_be.dto.GrammarDTO;
import com.englishweb.english_web_be.service.GrammarService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grammars")
@Slf4j
@Tag(name = "Grammar Controller")
@AllArgsConstructor
public class GrammarController {
    private final GrammarService service;
    private final GrammarMapper mapper;

    @Operation(method = "GET", summary = "Get grammar with status, sorting, and pagination",
            description = "Retrieve grammar entries with optional status filtering, sorting, and pagination")
    @GetMapping
    public ResponseEntity<Page<GrammarResponseDTO>> findGrammarWithStatusAndPagingAndSorting(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) StatusEnum status) {
        Page<GrammarDTO> grammarDTOPage = service.findGrammarWithStatusAndPagingAndSorting(
                status, page, size, sortBy, sortDir, GrammarDTO.class);
        Page<GrammarResponseDTO> responsePage = grammarDTOPage.map(mapper::mapToResponseDTO);
        return new ResponseEntity<>(responsePage, HttpStatus.OK);
    }

    @Operation(method = "GET", summary = "Get grammar by id", description = "Retrieve grammar information by ID")
    @GetMapping("/{id}")
    public ResponseEntity<GrammarResponseDTO> findById(@PathVariable String id) {
        GrammarDTO grammarDTO = service.findById(id);
        GrammarResponseDTO responseDTO = mapper.mapToResponseDTO(grammarDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new grammar", description = "Create a new grammar entry")
    @PostMapping
    public ResponseEntity<GrammarResponseDTO> create(@Valid @RequestBody GrammarRequestDTO requestDTO) {
        GrammarDTO grammarDTO = mapper.mapToDTO(requestDTO);
        GrammarDTO createdGrammar = service.create(grammarDTO);
        GrammarResponseDTO responseDTO = mapper.mapToResponseDTO(createdGrammar);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update grammar", description = "Update an existing grammar entry")
    @PutMapping("/{id}")
    public ResponseEntity<GrammarResponseDTO> update(@Valid @RequestBody GrammarRequestDTO requestDTO, @PathVariable String id) {
        GrammarDTO grammarDTO = mapper.mapToDTO(requestDTO);
        GrammarDTO updatedGrammar = service.update(grammarDTO, id);
        GrammarResponseDTO responseDTO = mapper.mapToResponseDTO(updatedGrammar);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete grammar", description = "Delete a grammar entry by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
