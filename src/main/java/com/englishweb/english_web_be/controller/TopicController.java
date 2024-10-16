package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TopicDTO;
import com.englishweb.english_web_be.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TopicController {
    TopicService service;

    public TopicController(TopicService service) {
        this.service = service;
    }

    @GetMapping("/api/topics")
    public ResponseEntity<Page<TopicDTO>> findByPage(@RequestParam int page,
                                                               @RequestParam int size,
                                                               @RequestParam(defaultValue = "id") String sortBy,
                                                               @RequestParam(defaultValue = "asc") String sortDir) {
        return new ResponseEntity<>(service.findByPage(page, size, sortBy, sortDir, TopicDTO.class), HttpStatus.OK);
    }
    @GetMapping("/api/topics/{id}")
    public ResponseEntity<TopicDTO> findById(@PathVariable String id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/topics")
    public ResponseEntity<TopicDTO> create(@Valid @RequestBody TopicDTO topicDTO){
        TopicDTO created = service.create(topicDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/api/topics")
    public ResponseEntity<TopicDTO> update(@Valid @RequestBody TopicDTO topicDTO){
        TopicDTO updated = service.update(topicDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/api/topics/{id}")
    public ResponseEntity<String> delete(@PathVariable String id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

