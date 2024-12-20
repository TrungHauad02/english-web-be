package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.service.FirebaseStorageService;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class FirebaseStorageServiceImpl implements FirebaseStorageService {

    private final String BUCKET_NAME = "englishweb-5a6ce.appspot.com";
    public enum RandomName {
        YES,
        NO;

        public static RandomName fromString(String value) {
            if (value != null) {
            for (RandomName randomName : RandomName.values()) {
                if (value.equalsIgnoreCase(randomName.name())) {
                    return randomName;
                }
            }
        }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    @Override
    public String uploadFile(String path, String fileName, byte[] bytes, String mimeType, RandomName randomName) throws IOException {
        // Generate a unique file name
        String uniqueFileName = fileName;
        if(randomName.equals(RandomName.YES)) uniqueFileName += "-" + UUID.randomUUID().toString();

        if (mimeType == null || mimeType.isEmpty()) {
            mimeType = "application/octet-stream";
        }

        // Upload the file
        Bucket bucket = StorageClient.getInstance().bucket(BUCKET_NAME);
        bucket.create(path + "/" + uniqueFileName, bytes, mimeType);
        log.info("File uploaded successfully!");
        // Get the public URL
        path = path.replace("/", "%2F");
        return "https://firebasestorage.googleapis.com/v0/b/" + BUCKET_NAME + "/o/" + path + "%2F" + uniqueFileName + "?alt=media";
    }

    public void deleteFile(String fileUrl) throws IOException {
        if (fileUrl == null || !fileUrl.contains("o/")) {
            return;
        }
        String path = fileUrl.split("o/")[1].split("\\?")[0];

        Bucket bucket = StorageClient.getInstance().bucket(BUCKET_NAME);
        if (bucket != null) {
            path = path.replace("%2F", "/");
            com.google.cloud.storage.Blob blob = bucket.get(path);
            if (blob != null) {
                boolean deleted = blob.delete();
                if (deleted) {
                    log.info("File deleted successfully!");
                } else {
                    log.error("Failed to delete the file.");
                }
            } else {
                log.error("File not found: {}", path);
            }
        }
    }

    public byte[] downloadFile(String fileUrl) throws IOException {
        if (fileUrl == null || !fileUrl.contains("o/")) {
            log.error("Invalid file URL: {}", fileUrl);
            throw new IllegalArgumentException("Invalid file URL.");
        }

        String path = fileUrl.split("o/")[1].split("\\?")[0];
        path = path.replace("%2F", "/");

        Bucket bucket = StorageClient.getInstance().bucket(BUCKET_NAME);
        Blob blob = bucket.get(path);

        if (blob == null) {
            throw new IOException("File not found: " + path);
        }

        return blob.getContent();
    }
}
