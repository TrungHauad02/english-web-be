package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.GrammarDTO;
import com.englishweb.english_web_be.service.GrammarService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
public class GrammarController {
    GrammarService service;

    public GrammarController(GrammarService service) {
        this.service = service;
    }

    @GetMapping("/grammars")
    public Page<GrammarDTO> retrieveGrammarsByPage(@RequestParam int page, @RequestParam int size){
        return service.retrieveGrammarsByPage(page, size, Sort.by("Serial"));
    }

    @GetMapping("/grammars/{id}")
    public GrammarDTO retrieveGrammarById(@PathVariable String id){
        return service.retrieveGrammarById(id);
    }

    @PostMapping("/grammars")
    public GrammarDTO createGrammar(@RequestBody GrammarDTO grammarDTO){
        return service.createGrammar(grammarDTO);
    }

    @PutMapping("/grammars")
    public GrammarDTO updateGrammar(@RequestBody GrammarDTO grammarDTO){
        return service.updateGrammar(grammarDTO);
    }

    @DeleteMapping("/grammars/{id}")
    public void deleteGrammarById(@PathVariable String id, HttpServletResponse response){
        if(service.deleteGrammar(id))
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        else
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
