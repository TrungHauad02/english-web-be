package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.GrammarDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.englishweb.english_web_be.service.GrammarService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grammars")
@Slf4j
@Tag(name = "Grammar Controller")
public class GrammarController {
    GrammarService service;

    public GrammarController(GrammarService service) {
        this.service = service;
    }

    @Operation(method = "GET", summary = "Get grammar with status, sorting and pagination",
            description = "Send a request via this API to get grammar with status, sorting and pagination. Status is optional")
    @GetMapping
    public ResponseEntity<Page<GrammarDTO>> findGrammarWithStatusAndPagingAndSorting(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) StatusEnum status) {

        Page<GrammarDTO> grammarPage = service.findWithPagingSortingSearching(
                title, status, page, size, sortBy, sortDir, GrammarDTO.class);

        return new ResponseEntity<>(grammarPage, HttpStatus.OK);
    }


    @Operation(method = "GET", summary = "Get grammar by id",
            description = "Send a request via this API to get grammar information")
    @GetMapping("/{id}")
    public ResponseEntity<GrammarDTO> findById(@PathVariable String id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new grammar",
            description = "Send a request via this API to create grammar")
    @PostMapping
    public ResponseEntity<GrammarDTO> create(@Valid @RequestBody GrammarDTO dto){
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update grammar",
            description = "Send a request via this API to update grammar")
    @PutMapping("/{id}")
    public ResponseEntity<GrammarDTO> update(@Valid @RequestBody GrammarDTO dto, @PathVariable String id){
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete grammar",
            description = "Send a request via this API to delete grammar")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
