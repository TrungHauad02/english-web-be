package com.englishweb.english_web_be.controller;

import com.englishweb.english_web_be.dto.request.UploadRequest;
import com.englishweb.english_web_be.dto.response.UploadResponse;
import com.englishweb.english_web_be.service.FirebaseStorageService;
import com.englishweb.english_web_be.service.impl.FirebaseStorageServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/upload-file")
@Tag(name = "Upload File Controller")
public class UploadFileController {
    FirebaseStorageService service;

    @Operation(method = "POST", summary = "Upload file",
            description = "Send a request via this API to upload a file to firebase storage")
    @PostMapping
    public ResponseEntity<UploadResponse> uploadFile(@RequestBody UploadRequest request,
                                                     @RequestParam(required = false, defaultValue = "YES") String randomName) {
        try {
            FirebaseStorageServiceImpl.RandomName randomNameEnum = FirebaseStorageServiceImpl.RandomName.fromString(randomName);

            String fileData = request.getFile();
            if (fileData == null || !fileData.contains(",")) {
                return ResponseEntity.badRequest().build();
            }
            String base64Data = fileData.split(",")[1];
            byte[] fileBytes = Base64.getDecoder().decode(base64Data);
            String mimeType = fileData.split(",")[0].split(":")[1].split(";")[0];

            String url = service.uploadFile(request.getPath(), request.getFileName(), fileBytes, mimeType, randomNameEnum);
            return ResponseEntity.ok(new UploadResponse(url));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Operation(method = "DELETE", summary = "Delete file",
            description = "Send a request via this API to delete a file from firebase storage")
    @DeleteMapping
    public ResponseEntity<Void> deleteFile(@RequestBody String fileUrl){
        try {
            service.deleteFile(fileUrl);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestBody Map<String, String> request) {
        String fileUrl = request.get("fileUrl");

        try {
            byte[] fileData = service.downloadFile(fileUrl);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"file.pdf\"")
                    .body(fileData);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
