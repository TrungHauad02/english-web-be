package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.ListeningDTO;
import com.englishweb.english_web_be.service.ListeningService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ListeningController {
    ListeningService service;

    public ListeningController(ListeningService service) {
        this.service = service;
    }

    @GetMapping("/api/listenings")
    public ResponseEntity<Page<ListeningDTO>> findByPage(@RequestParam int page,
                                                         @RequestParam int size,
                                                         @RequestParam(defaultValue = "id") String sortBy,
                                                         @RequestParam(defaultValue = "asc") String sortDir){
        return new ResponseEntity<>(service.findByPage(page, size, sortBy, sortDir,ListeningDTO.class), HttpStatus.OK);
    }

    @GetMapping("/api/listenings/{id}")
    public ResponseEntity<ListeningDTO> findById(@PathVariable String id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/listenings")
    public ResponseEntity<ListeningDTO> save(@Valid @RequestBody ListeningDTO dto){
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/api/listenings/{id}")
    public ResponseEntity<ListeningDTO> update(@Valid @RequestBody ListeningDTO dto, @PathVariable String id){
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/api/listenings/{id}")
    public ResponseEntity<ListeningDTO> delete(@PathVariable String id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}