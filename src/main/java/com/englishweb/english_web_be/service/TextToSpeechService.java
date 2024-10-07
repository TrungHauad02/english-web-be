package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.util.FileUtils;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;


@Service
public class TextToSpeechService {

    public String convertTextToSpeech(String text) throws Exception {
        String jsonPath = "src/main/resources/ttsServiceAccountKey.json";
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath));
        TextToSpeechSettings settings = TextToSpeechSettings.newBuilder()
                .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                .build();
        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create(settings)) {
            SynthesisInput input = SynthesisInput.newBuilder()
                    .setText(text)
                    .build();
            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                    .setLanguageCode("en-US")
                    .setSsmlGender(SsmlVoiceGender.NEUTRAL)
                    .build();
            AudioConfig audioConfig = AudioConfig.newBuilder()
                    .setAudioEncoding(AudioEncoding.MP3)
                    .build();

            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);
            ByteString audioContents = response.getAudioContent();

            return FileUtils.writeSoundBytesToFile(audioContents.toByteArray());
        }
    }
}
