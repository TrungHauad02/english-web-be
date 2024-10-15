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

    @GetMapping("/grammars")
    public ResponseEntity<Page<GrammarDTO>> retrieveGrammarsByPage(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(service.retrieveGrammarsByPage(page, size), HttpStatus.OK);
    }

    @GetMapping("/grammars/{id}")
    public ResponseEntity<GrammarDTO> retrieveGrammarById(@PathVariable String id){
        return new ResponseEntity<>(service.retrieveGrammarById(id), HttpStatus.OK);
    }

    @PostMapping("/grammars")
    public ResponseEntity<GrammarDTO> createGrammar(@Valid @RequestBody GrammarDTO dto){
        GrammarDTO createdGrammar = service.createGrammar(dto);
        return new ResponseEntity<>(createdGrammar, HttpStatus.CREATED);
    }

    @PutMapping("/grammars")
    public ResponseEntity<GrammarDTO> updateGrammar(@Valid @RequestBody GrammarDTO dto){
        GrammarDTO updatedGrammar = service.updateGrammar(dto);
        return ResponseEntity.ok(updatedGrammar);
    }

    @DeleteMapping("/grammars/{id}")
    public ResponseEntity<Void> deleteGrammarById(@PathVariable String id){
        service.deleteGrammar(id);
        return ResponseEntity.noContent().build();
    }
}
