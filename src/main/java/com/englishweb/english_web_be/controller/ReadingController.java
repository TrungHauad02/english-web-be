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


}
