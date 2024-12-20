package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.response.SpeechToTextResponse;
import com.englishweb.english_web_be.service.SpeechToTextService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/speech-to-text")
@AllArgsConstructor
public class SpeechToTextController {
    SpeechToTextService service;

    @PostMapping
    public ResponseEntity<SpeechToTextResponse> speechToText(@RequestBody AudioData request, @RequestParam (required = false, defaultValue = "1") int channel_counts) {
        String audioData = request.getAudioData();
        if (audioData == null || !audioData.contains(",")) {
            return ResponseEntity.badRequest().build();
        }
        String base64Data = audioData.split(",")[1];
        if (!Base64.isBase64(base64Data)) {
            throw new IllegalArgumentException("Invalid Base64 encoding");
        }
        try {
            return new ResponseEntity<>(service.speechToText(base64Data, channel_counts), HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Getter
    private static class AudioData {
        private String audioData;
    }
}
