package com.englishweb.english_web_be.service;

import java.io.IOException;

public interface FirebaseStorageService {

    String uploadFile(String path, String fileName, byte[] bytes, String mimeType) throws IOException;

    void deleteFile(String fileUrl) throws IOException;

    byte[] downloadFile(String fileUrl) throws IOException;
}
