package com.englishweb.english_web_be.config;

import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;


import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {
    @Value("${FIREBASE_CONFIG_PATH}")
    private String firebaseConfigPath;
    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        //String path = "src/main/resources/englishweb-firebase-adminsdk.json";
        String path = firebaseConfigPath;
        if (FirebaseApp.getApps().isEmpty()) {
            FileInputStream serviceAccount =
                    new FileInputStream(path);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://englishweb-5a6ce-default-rtdb.firebaseio.com")
                    .setStorageBucket("englishweb-5a6ce.appspot.com")
                    .build();

            return FirebaseApp.initializeApp(options);
        }
        return FirebaseApp.getInstance();
    }
}