package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.ListenPracticeDTO;
import com.englishweb.english_web_be.service.ListenPracticeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ListenPracticeController {
    ListenPracticeService service;

    public ListenPracticeController(ListenPracticeService service) {
        this.service = service;
    }

    @GetMapping("/api/listenPractice/{id}")
    public ResponseEntity<ListenPracticeDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/api/listenPractice")
    public ResponseEntity<ListenPracticeDTO> findByListeningId(@RequestParam String listeningId) {
        return new ResponseEntity<>(service.findByListeningId(listeningId), HttpStatus.OK);
    }

    @PostMapping("/api/listenPractice")
    public ResponseEntity<ListenPracticeDTO> create(@Valid @RequestBody ListenPracticeDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/api/listenPractice/{id}")
    public ResponseEntity<ListenPracticeDTO> update(@Valid @RequestBody ListenPracticeDTO dto, @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/api/listenPractice/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
