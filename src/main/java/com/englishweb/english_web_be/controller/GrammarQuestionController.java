package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.GrammarQuestionDTO;
import com.englishweb.english_web_be.service.GrammarQuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GrammarQuestionController {
    GrammarQuestionService service;

    public GrammarQuestionController(GrammarQuestionService service) {
        this.service = service;
    }

    @GetMapping("/api/grammar-question")
    public ResponseEntity<List<GrammarQuestionDTO>> findByGrammarId(@RequestParam String grammarId) {
        return new ResponseEntity<>(service.findAllByGrammarId(grammarId), HttpStatus.OK);
    }

    @PostMapping("/api/grammar-question")
    public ResponseEntity<GrammarQuestionDTO> create(@Valid @RequestBody GrammarQuestionDTO dto){
        GrammarQuestionDTO created = service.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/api/grammar-question")
    public ResponseEntity<GrammarQuestionDTO> update(@Valid @RequestBody GrammarQuestionDTO dto){
        GrammarQuestionDTO updated = service.update(dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/api/grammar-question/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
