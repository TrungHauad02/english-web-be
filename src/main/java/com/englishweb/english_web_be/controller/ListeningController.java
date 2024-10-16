package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.ListeningDTO;
import com.englishweb.english_web_be.service.ListeningService;
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

    @GetMapping("/api/listening")
    public ResponseEntity<Page<ListeningDTO>> findByPage(@RequestParam int page,
                                                         @RequestParam int size,
                                                         @RequestParam(defaultValue = "id") String sortBy,
                                                         @RequestParam(defaultValue = "asc") String sortDir){
        return new ResponseEntity<>(service.findByPage(page, size, sortBy, sortDir,ListeningDTO.class), HttpStatus.OK);
    }

    @GetMapping("/api/listening/{id}")
    public ResponseEntity<ListeningDTO> findById(@PathVariable String id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/listening")
    public ResponseEntity<ListeningDTO> save(@RequestBody ListeningDTO dto){
        ListeningDTO created = service.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/api/listening")
    public ResponseEntity<ListeningDTO> update(@RequestBody ListeningDTO dto){
        ListeningDTO updated = service.update(dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/api/listening/{id}")
    public ResponseEntity<ListeningDTO> delete(@PathVariable String id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}