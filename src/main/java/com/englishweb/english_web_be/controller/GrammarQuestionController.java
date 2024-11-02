package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.GrammarQuestionDTO;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.service.GrammarQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grammar-question")
@Slf4j
@Tag(name = "GrammarQuestion Controller")
public class GrammarQuestionController {
    GrammarQuestionService service;

    public GrammarQuestionController(GrammarQuestionService service) {
        this.service = service;
    }

    @Operation(method = "GET", summary = "Get grammar questions by grammar id and status",
            description = "Send a request via this API to get grammar questions by grammar id and status")
    @GetMapping
    public ResponseEntity<List<GrammarQuestionDTO>> findByGrammarId(
                                        @RequestParam String grammarId,
                                        @RequestParam(required = false) StatusEnum status) {
        return new ResponseEntity<>(service.findAllByGrammarIdAndStatus(grammarId, status), HttpStatus.OK);
    }

    @Operation(method = "GET", summary = "Get grammar question by id",
            description = "Send a request via this API to get grammar question by id")
    @GetMapping("/{id}")
    public ResponseEntity<GrammarQuestionDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new grammar question",
            description = "Send a request via this API to create new grammar question")
    @PostMapping
    public ResponseEntity<GrammarQuestionDTO> create(@Valid @RequestBody GrammarQuestionDTO dto){
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update grammar question",
            description = "Send a request via this API to update grammar question")
    @PutMapping("/{id}")
    public ResponseEntity<GrammarQuestionDTO> update(@Valid @RequestBody GrammarQuestionDTO dto, @PathVariable String id){
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete grammar question",
            description = "Send a request via this API to delete grammar question")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
