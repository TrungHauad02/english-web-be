package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.GrammarDTO;
import com.englishweb.english_web_be.service.GrammarService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GrammarController {
    GrammarService service;

    public GrammarController(GrammarService service) {
        this.service = service;
    }

    @GetMapping("/api/grammars")
    public ResponseEntity<Page<GrammarDTO>> retrieveGrammarsByPage(@RequestParam int page,
                                                                   @RequestParam int size,
                                                                   @RequestParam(defaultValue = "serial") String sortBy,
                                                                   @RequestParam(defaultValue = "asc") String sortDir){
        return new ResponseEntity<>(service.findByPage(page, size, sortBy, sortDir, GrammarDTO.class), HttpStatus.OK);
    }

    @GetMapping("/api/grammars/{id}")
    public ResponseEntity<GrammarDTO> retrieveGrammarById(@PathVariable String id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/grammars")
    public ResponseEntity<GrammarDTO> createGrammar(@Valid @RequestBody GrammarDTO dto){
        GrammarDTO createdGrammar = service.create(dto);
        return new ResponseEntity<>(createdGrammar, HttpStatus.CREATED);
    }

    @PutMapping("/api/grammars")
    public ResponseEntity<GrammarDTO> updateGrammar(@Valid @RequestBody GrammarDTO dto){
        GrammarDTO updatedGrammar = service.update(dto);
        return ResponseEntity.ok(updatedGrammar);
    }

    @DeleteMapping("/api/grammars/{id}")
    public ResponseEntity<Void> deleteGrammarById(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
