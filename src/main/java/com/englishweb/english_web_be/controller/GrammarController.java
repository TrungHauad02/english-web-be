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
    public Page<GrammarDTO> retrieveGrammarsByPage(@RequestParam int page, @RequestParam int size, HttpServletResponse response){
        if(size <= 0)
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        return service.retrieveGrammarsByPage(page, size, Sort.by("serial"));
    }

    @GetMapping("/grammars/{id}")
    public GrammarDTO retrieveGrammarById(@PathVariable String id, HttpServletResponse response){
        GrammarDTO dto = service.retrieveGrammarById(id);
        if(dto == null){
            dto = new GrammarDTO();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return dto;
        }
        response.setStatus(HttpServletResponse.SC_OK);
        return dto;
    }

    @PostMapping("/grammars")
        public GrammarDTO createGrammar(@RequestBody GrammarDTO grammarDTO){
        return service.createGrammar(grammarDTO);
    }

    @PutMapping("/grammars")
    public GrammarDTO updateGrammar(@RequestBody GrammarDTO grammarDTO, HttpServletResponse response){
        GrammarDTO dto = service.updateGrammar(grammarDTO);
        if(dto == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        response.setStatus(HttpServletResponse.SC_OK);
        return dto;
    }

    @DeleteMapping("/grammars/{id}")
    public void deleteGrammarById(@PathVariable String id, HttpServletResponse response){
        if(service.deleteGrammar(id))
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        else
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
