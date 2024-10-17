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

    @GetMapping("/api/listenAndWriteAWord")
    public ResponseEntity<List<ListenAndWriteAWordDTO>> getByListeningId(String listeningId) {
        List<ListenAndWriteAWordDTO> dtoList = service.findByListeningId(listeningId);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PostMapping("/api/listenAndWriteAWord")
    public ResponseEntity<ListenAndWriteAWordDTO> create(@Valid @RequestBody ListenAndWriteAWordDTO dto) {
        ListenAndWriteAWordDTO created = service.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/api/listenAndWriteAWord")
    public ResponseEntity<ListenAndWriteAWordDTO> update(@Valid @RequestBody ListenAndWriteAWordDTO dto) {
        ListenAndWriteAWordDTO updated = service.update(dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/api/listenAndWriteAWord/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
