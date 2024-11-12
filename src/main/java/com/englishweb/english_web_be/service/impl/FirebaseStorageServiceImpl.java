package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.service.FirebaseStorageService;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class FirebaseStorageServiceImpl implements FirebaseStorageService {

    private final String BUCKET_NAME = "englishweb-5a6ce.appspot.com";

    @Override
    public String uploadFile(String path, String fileName, byte[] bytes, String mimeType) throws IOException {
        // Generate a unique file name
        String uniqueFileName = fileName + "-" + UUID.randomUUID().toString();

        if (mimeType == null || mimeType.isEmpty()) {
            mimeType = "application/octet-stream";
        }

        // Upload the file
        Bucket bucket = StorageClient.getInstance().bucket(BUCKET_NAME);
        bucket.create(path + "/" + uniqueFileName, bytes, mimeType);
        // Get the public URL
        path = path.replace("/", "%2F");
        return "https://firebasestorage.googleapis.com/v0/b/" + BUCKET_NAME + "/o/" + path + "%2F" + uniqueFileName + "?alt=media";
    }

    public void deleteFile(String fileUrl) throws IOException {
        String path = fileUrl.split("o/")[1].split("\\?")[0];

        Bucket bucket = StorageClient.getInstance().bucket(BUCKET_NAME);
        if (bucket != null) {
            path = path.replace("%2F", "/");
            com.google.cloud.storage.Blob blob = bucket.get(path);
            if (blob != null) {
                boolean deleted = blob.delete();
                if (deleted) {
                    System.out.println("File deleted successfully!");
                } else {
                    System.out.println("Failed to delete the file.");
                }
            } else {
                System.out.println("File not found: " + path);
            }
        }
    }
}
