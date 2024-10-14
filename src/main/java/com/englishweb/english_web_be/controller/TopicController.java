package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TopicDTO;
import com.englishweb.english_web_be.service.TopicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RestController
public class TopicController {
    TopicService service;

    public TopicController(TopicService service) {
        this.service = service;
    }

    @GetMapping("/topics")
    public Page<TopicDTO> retrieveTopicsByPage(@RequestParam int page, @RequestParam int size) {
        return service.retrieveTopicsByPage(page, size, Sort.by("serial"));
    }
    @GetMapping("/topics/{id}")
    public TopicDTO retrieveTopicById(@PathVariable String id){
        return service.retrieveTopicById(id);
    }
}
