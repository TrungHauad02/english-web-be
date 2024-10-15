package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.ReadingDTO;
import com.englishweb.english_web_be.service.ReadingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReadingController {
    ReadingService service;

    public ReadingController(ReadingService service) {
        this.service = service;
    }

    @GetMapping("/readings")
    public Page<ReadingDTO> retrieveReadingByPage(@RequestParam int page, @RequestParam int size) {
        return service.retrieveReadingsByPage(page, size, Sort.by("serial"));
    }

    @GetMapping("/readings/{id}")
    public ReadingDTO retrieveReadingById(@PathVariable String id) {
        return service.retrieveReadingById(id);
    }

    @PostMapping("/readings")
    public ReadingDTO createReading(@RequestBody ReadingDTO dto) {
        return service.createReading(dto);
    }

    @PutMapping("/readings")
    public ReadingDTO updateReading(@RequestBody ReadingDTO dto) {
        return service.updateReading(dto);
    }

    @DeleteMapping("/reading/{id}")
    public void deleteReading(@PathVariable String id) {
        service.deleteReading(id);
    }
}
