package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.WritingDTO;
import com.englishweb.english_web_be.service.WritingService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WritingController {
    WritingService service;

    public WritingController(WritingService service) {
        this.service = service;
    }

    @GetMapping("/api/writings")
    public ResponseEntity<Page<WritingDTO>> findByPage(@RequestParam int page,
                                                       @RequestParam int size,
                                                       @RequestParam(defaultValue = "id") String sortBy,
                                                       @RequestParam(defaultValue = "asc") String sortDir) {
        return new ResponseEntity<>(service.findByPage(page, size, sortBy, sortDir, WritingDTO.class), HttpStatus.OK);
    }

    @GetMapping("/api/writings/{id}")
    public ResponseEntity<WritingDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/writings")
    public ResponseEntity<WritingDTO> create(@Valid @RequestBody WritingDTO dto){
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/api/writings/{id}")
    public ResponseEntity<WritingDTO> update(@Valid @RequestBody WritingDTO dto, @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/api/writings/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
