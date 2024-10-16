package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.ReadingDTO;
import com.englishweb.english_web_be.service.ReadingService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReadingController {
    ReadingService service;

    public ReadingController(ReadingService service) {
        this.service = service;
    }

    @GetMapping("/api/readings")
    public ResponseEntity<Page<ReadingDTO>> findByPage(@RequestParam int page,
                                                       @RequestParam int size,
                                                       @RequestParam(defaultValue = "id") String sortBy,
                                                       @RequestParam(defaultValue = "asc") String sortDir) {
        return new ResponseEntity<>(service.findByPage(page, size, sortBy, sortDir, ReadingDTO.class), HttpStatus.OK);
    }

    @GetMapping("/api/readings/{id}")
    public ResponseEntity<ReadingDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/readings")
    public ResponseEntity<ReadingDTO> create(@Valid @RequestBody ReadingDTO dto){
        ReadingDTO created = service.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/api/readings")
    public ResponseEntity<ReadingDTO> update(@Valid @RequestBody ReadingDTO dto){
        ReadingDTO updated = service.update(dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/api/readings/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
