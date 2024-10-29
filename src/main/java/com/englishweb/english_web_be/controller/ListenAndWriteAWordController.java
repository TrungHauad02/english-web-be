package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.ListenAndWriteAWordDTO;
import com.englishweb.english_web_be.service.ListenAndWriteAWordService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ListenAndWriteAWordController {
    ListenAndWriteAWordService service;

    public ListenAndWriteAWordController(ListenAndWriteAWordService service) {
        this.service = service;
    }

    @GetMapping("/api/listenAndWriteAWord/{id}")
    public ResponseEntity<ListenAndWriteAWordDTO> getById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/api/listenAndWriteAWord")
    public ResponseEntity<List<ListenAndWriteAWordDTO>> getByListeningId(@RequestParam String listeningId) {
        return new ResponseEntity<>(service.findByListeningId(listeningId), HttpStatus.OK);
    }

    @PostMapping("/api/listenAndWriteAWord")
    public ResponseEntity<ListenAndWriteAWordDTO> create(@Valid @RequestBody ListenAndWriteAWordDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/api/listenAndWriteAWord/{id}")
    public ResponseEntity<ListenAndWriteAWordDTO> update(@Valid @RequestBody ListenAndWriteAWordDTO dto, @PathVariable String id) {
        return new ResponseEntity<>(service.update(dto,id), HttpStatus.OK);
    }

    @DeleteMapping("/api/listenAndWriteAWord/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
