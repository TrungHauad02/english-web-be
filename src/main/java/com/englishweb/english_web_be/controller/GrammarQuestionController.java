package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.request.GrammarQuestionRequestDTO;
import com.englishweb.english_web_be.dto.response.GrammarQuestionResponseDTO;
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
    private final GrammarQuestionService service;

    public GrammarQuestionController(GrammarQuestionService service) {
        this.service = service;
    }

    @Operation(method = "GET", summary = "Get grammar questions by grammar id and status",
            description = "Send a request via this API to get grammar questions by grammar id and status")
    @GetMapping
    public ResponseEntity<List<GrammarQuestionResponseDTO>> findByGrammarId(
            @RequestParam String grammarId,
            @RequestParam(required = false) StatusEnum status) {
        List<GrammarQuestionResponseDTO> questions = service.findAllByGrammarIdAndStatus(grammarId, status);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @Operation(method = "GET", summary = "Get grammar question by id",
            description = "Send a request via this API to get grammar question by id")
    @GetMapping("/{id}")
    public ResponseEntity<GrammarQuestionResponseDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Create new grammar question",
            description = "Send a request via this API to create new grammar question")
    @PostMapping
    public ResponseEntity<GrammarQuestionResponseDTO> create(@Valid @RequestBody GrammarQuestionRequestDTO dto){
        GrammarQuestionResponseDTO createdQuestion = service.create(dto);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update grammar question",
            description = "Send a request via this API to update grammar question")
    @PutMapping("/{id}")
    public ResponseEntity<GrammarQuestionResponseDTO> update(@Valid @RequestBody GrammarQuestionRequestDTO dto, @PathVariable String id){
        GrammarQuestionResponseDTO updatedQuestion = service.update(dto, id);
        return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
    }

    @Operation(method = "DELETE", summary = "Delete grammar question",
            description = "Send a request via this API to delete grammar question")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
