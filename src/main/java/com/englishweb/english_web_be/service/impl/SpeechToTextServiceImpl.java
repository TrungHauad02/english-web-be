package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.response.SpeechToTextResponse;
import com.englishweb.english_web_be.service.SpeechToTextService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.*;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.protobuf.ByteString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Base64;
import java.util.List;

@Service
@Slf4j
public class SpeechToTextServiceImpl implements SpeechToTextService {

    @Value("${SPEECH_TO_TEXT_CONFIG_PATH}")
    private String ttsConfigPath;
    @Override
    public SpeechToTextResponse speechToText(String audioBase64, int channel_counts) throws IOException {

        //String credentialsPath = "src/main/resources/englishweb-speech-to-text.json";
        String credentialsPath = ttsConfigPath;
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(credentialsPath));
        SpeechSettings speechSettings = SpeechSettings.newBuilder()
                .setCredentialsProvider(() -> credentials)
                .build();
        try (SpeechClient speechClient = SpeechClient.create(speechSettings)) {
            byte[] audioBytes = Base64.getDecoder().decode(audioBase64);

            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(AudioEncoding.WEBM_OPUS)
                    .setSampleRateHertz(48000)
                    .setModel("latest_long")
                    .setLanguageCode("en-US")
                    .setEnableWordTimeOffsets(true)
                    .setEnableWordConfidence(true)
                    .setAudioChannelCount(channel_counts)
                    .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(ByteString.copyFrom(audioBytes)).build();
            RecognizeResponse response = speechClient.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();
            if (results.isEmpty()) {
                log.info("Speech To Text: No results found");
                return SpeechToTextResponse.builder().build();
            }
            SpeechRecognitionResult result = results.get(0);
            if (result != null) {
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                log.info("Speech To Text: Transcript: {}, Confidence: {}", alternative.getTranscript(), alternative.getConfidence());
                return SpeechToTextResponse.builder()
                        .transcript(alternative.getTranscript())
                        .confidence((double) alternative.getConfidence())
                        .build();
            }
            log.info("Speech To Text: No transcript found");
            return SpeechToTextResponse.builder().build();
        } catch (Exception e){
            log.error("Speech To Text: Error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
