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
    public ResponseEntity<Page<GrammarDTO>> findByPage(@RequestParam int page,
                                                               @RequestParam int size,
                                                               @RequestParam(defaultValue = "id") String sortBy,
                                                               @RequestParam(defaultValue = "asc") String sortDir){
        return new ResponseEntity<>(service.findByPage(page, size, sortBy, sortDir, GrammarDTO.class), HttpStatus.OK);
    }

    @GetMapping("/api/grammars/{id}")
    public ResponseEntity<GrammarDTO> findById(@PathVariable String id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/grammars")
    public ResponseEntity<GrammarDTO> create(@Valid @RequestBody GrammarDTO dto){
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/api/grammars/{id}")
    public ResponseEntity<GrammarDTO> update(@Valid @RequestBody GrammarDTO dto, @PathVariable String id){
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/api/grammars/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
