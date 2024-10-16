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
        return new ResponseEntity<>(service.findTopicQuestionByTopicId(topicId), HttpStatus.OK);
    }

    @PostMapping("/api/topics-question")
    public ResponseEntity<TopicQuestionDTO> create(@Valid @RequestBody TopicQuestionDTO dto){
        TopicQuestionDTO created = service.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/api/topics-question")
    public ResponseEntity<TopicQuestionDTO> update(@Valid @RequestBody TopicQuestionDTO dto){
        TopicQuestionDTO updated = service.update(dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/api/topics-question/{id}")
    public ResponseEntity<TopicQuestionDTO> delete(@PathVariable String id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
