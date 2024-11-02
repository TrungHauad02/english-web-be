package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.GrammarAnswerDTO;
import com.englishweb.english_web_be.dto.request.GrammarAnswerRequestDTO;
import com.englishweb.english_web_be.dto.response.GrammarAnswerResponseDTO;
import com.englishweb.english_web_be.mapper.GrammarAnswerMapper;
import com.englishweb.english_web_be.service.GrammarAnswerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class GrammarAnswerController {
    GrammarAnswerService service;
    GrammarAnswerMapper mapper;

    @PostMapping("/api/grammar-answer")
    public ResponseEntity<GrammarAnswerResponseDTO> create(@Valid @RequestBody GrammarAnswerRequestDTO requestDTO) {

        GrammarAnswerDTO dto = mapper.mapToDTO(requestDTO);
        GrammarAnswerDTO createdDTO = service.create(dto);
        GrammarAnswerResponseDTO responseDTO = mapper.mapToResponseDTO(createdDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/api/grammar-answer/{id}")
    public ResponseEntity<GrammarAnswerResponseDTO> update(
            @Valid @RequestBody GrammarAnswerRequestDTO requestDTO,
            @PathVariable String id) {

        GrammarAnswerDTO dto = mapper.mapToDTO(requestDTO);
        GrammarAnswerDTO updatedDTO = service.update(dto, id);
        GrammarAnswerResponseDTO responseDTO = mapper.mapToResponseDTO(updatedDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/api/grammar-answer/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}