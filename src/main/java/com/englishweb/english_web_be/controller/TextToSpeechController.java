package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.TextToSpeechRequest;
import com.englishweb.english_web_be.dto.TextToSpeechResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import com.englishweb.english_web_be.service.FirebaseStorageService;
import com.englishweb.english_web_be.service.TextToSpeechService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
@RestController
@RequestMapping("/api/text-to-speech")
public class TextToSpeechController {

    private final TextToSpeechService textToSpeechService;
    private final FirebaseStorageService firebaseStorageService;

    public TextToSpeechController(TextToSpeechService textToSpeechService, FirebaseStorageService firebaseStorageService) {
        this.textToSpeechService = textToSpeechService;
        this.firebaseStorageService = firebaseStorageService;
    }

    @PostMapping
    public TextToSpeechResponse convertText(@RequestBody TextToSpeechRequest request) {
        try {
            //String audioFilePath = textToSpeechService.convertTextToSpeech(request.getText());

            String audioFilePath = "audio/1726995766240.mp3";
            Resource resource = new ClassPathResource(audioFilePath);
            File audioFile = resource.getFile();

            MultipartFile file = new MultipartFile() {
                @Override
                public String getName() {
                    return request.getFileName();
                }

                @Override
                public String getOriginalFilename() {
                    return getName();
                }

                @Override
                public String getContentType() {
                    return "audio/mpeg";
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public long getSize() {
                    return audioFile.length();
                }

                @Override
                public byte[] getBytes() throws IOException {
                    return Files.readAllBytes(audioFile.toPath());
                }

                @Override
                public InputStream getInputStream() throws IOException {
                    return new FileInputStream(audioFile);
                }

                @Override
                public void transferTo(File dest) throws IOException, IllegalStateException {

                }
            };
            String urlPublic = firebaseStorageService.uploadFile(file, "audio");
            if (audioFile.delete()) {
                System.out.println("File đã được xóa thành công: " + audioFile.getAbsolutePath());
            } else {
                System.out.println("Không thể xóa file: " + audioFile.getAbsolutePath());
            }
            return  new TextToSpeechResponse(urlPublic);
        } catch (Exception e) {
            e.printStackTrace();
            return new TextToSpeechResponse("Error: " + e.getMessage());
        }
    }
}
