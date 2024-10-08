package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.model.Topic;
import com.englishweb.english_web_be.repository.TopicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopicController {
    TopicRepository repository;
    public TopicController(TopicRepository repository){this.repository = repository;}
    @GetMapping("/topics")
    public Page<Topic> retrieveTopicsByPage(@RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10, Sort.by("serial"));
        return repository.findAllTopics(pageable);
    }
    @PostMapping("/topics")
    public Topic createTopic(@RequestBody Topic topic){
        return repository.save(topic);
    }
}
