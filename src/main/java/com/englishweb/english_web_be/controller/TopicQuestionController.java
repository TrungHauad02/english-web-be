package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TopicQuestionDTO;
import com.englishweb.english_web_be.service.TopicQuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopicQuestionController {

    TopicQuestionService service;

    public TopicQuestionController(TopicQuestionService service) {
        this.service = service;
    }

    @GetMapping("/api/topics-question")
    public ResponseEntity<List<TopicQuestionDTO>> findAllQuestionByTopicId(@RequestParam String topicId) {
        return new ResponseEntity<>(service.findAllByTopicId(topicId), HttpStatus.OK);
    }

    @PostMapping("/api/topics-question")
    public ResponseEntity<TopicQuestionDTO> create(@Valid @RequestBody TopicQuestionDTO dto){
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/api/topics-question/{id}")
    public ResponseEntity<TopicQuestionDTO> update(@Valid @RequestBody TopicQuestionDTO dto, @PathVariable String id){
        return new ResponseEntity<>(service.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/api/topics-question/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
