package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.service.impl.FirebaseStorageServiceImpl;

import java.io.IOException;

public interface FirebaseStorageService {

    String uploadFile(String path, String fileName, byte[] bytes, String mimeType, FirebaseStorageServiceImpl.RandomName randomName) throws IOException;

    void deleteFile(String fileUrl) throws IOException;

    byte[] downloadFile(String fileUrl) throws IOException;
}
