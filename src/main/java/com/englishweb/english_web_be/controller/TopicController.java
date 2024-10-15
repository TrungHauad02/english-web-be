package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TopicDTO;
import com.englishweb.english_web_be.service.TopicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Page<TopicDTO>> retrieveTopicsByPage(@RequestParam int page,
                                                               @RequestParam int size,
                                                               @RequestParam(defaultValue = "serial") String sortBy,
                                                               @RequestParam(defaultValue = "asc") String sortDir) {
        return new ResponseEntity<>(service.retrieveTopicsByPage(page, size, sortBy, sortDir), HttpStatus.OK);
    }
    @GetMapping("/api/topics/{id}")
    public ResponseEntity<TopicDTO> retrieveTopicById(@PathVariable String id){
        return new ResponseEntity<>(service.retrieveTopicById(id), HttpStatus.FOUND);
    }
}
