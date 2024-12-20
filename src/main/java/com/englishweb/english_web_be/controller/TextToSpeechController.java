package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.service.TextToSpeechService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@RestController
@RequestMapping("/api/text-to-speech")
@Slf4j
@Tag(name = "TextToSpeech Controller")
public class TextToSpeechController {

    private final TextToSpeechService service;

    public TextToSpeechController(TextToSpeechService service) {
        this.service = service;
    }

    @Operation(method = "GET", summary = "Convert text to speech",
            description = "Converts the provided text to speech using the specified voice and returns the audio file as an MP3.")
    @GetMapping("/convert")
    public ResponseEntity<InputStreamResource> convertTextToSpeech(
            @RequestParam String text,
            @RequestParam String voice) {
        log.info("Received request to convert text to speech: {} with voice: {}", text, voice);

        try {
            InputStreamResource audio = service.convertTextToSpeech(text, voice);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"output.mp3\"");
            headers.add(HttpHeaders.CONTENT_TYPE, "audio/mpeg");

            return new ResponseEntity<>(audio, headers, HttpStatus.OK);

        } catch (IOException | InterruptedException e) {
            log.error("Error converting text to speech", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(method = "GET", summary = "Fetch available voices",
            description = "Fetches a list of available voices from the Play.ht API.")
    @GetMapping("/voices")
    public ResponseEntity<String> getVoices() {
        try {
            String voices = service.getAvailableVoices();
            return ResponseEntity.ok(voices);
        } catch (IOException | InterruptedException e) {
            return ResponseEntity.status(500).body("Error fetching voices: " + e.getMessage());
        }
    }
}
