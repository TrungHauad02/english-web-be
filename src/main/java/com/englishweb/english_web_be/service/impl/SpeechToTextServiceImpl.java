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
    public SpeechToTextResponse speechToText(String audioBase64) throws IOException {

        byte[] audioBytes = Base64.getDecoder().decode(audioBase64);
        File originalFile = File.createTempFile("original_audio", ".tmp");
        int channelCount;
        try (FileOutputStream fos = new FileOutputStream(originalFile)) {
            fos.write(audioBytes);

            channelCount = getAudioChannelCountUsingFFMPEG(originalFile);

            if (channelCount <= 0) {
                throw new RuntimeException("Invalid audio channel count detected");
            }

        }
        //String credentialsPath = "src/main/resources/englishweb-speech-to-text.json";
        String credentialsPath = ttsConfigPath;
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(credentialsPath));
        SpeechSettings speechSettings = SpeechSettings.newBuilder()
                .setCredentialsProvider(() -> credentials)
                .build();
        try (SpeechClient speechClient = SpeechClient.create(speechSettings)) {
            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(AudioEncoding.WEBM_OPUS)
                    .setSampleRateHertz(48000)
                    .setModel("latest_long")
                    .setLanguageCode("en-US")
                    .setAudioChannelCount(channelCount)
                    .setEnableWordTimeOffsets(true)
                    .setEnableWordConfidence(true)
                    .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(ByteString.copyFrom(audioBytes)).build();
            RecognizeResponse response = speechClient.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();
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
        private int getAudioChannelCountUsingFFMPEG(File audioFile) throws IOException {
            String command = String.format("ffprobe -v error -select_streams a:0 -show_entries stream=channels -of csv=p=0 %s", audioFile.getAbsolutePath());
            Process process = Runtime.getRuntime().exec(command);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line = reader.readLine();
                return line != null ? Integer.parseInt(line.trim()) : -1;
            } catch (Exception e) {
                log.error("Failed to get audio channel count: {}", e.getMessage());
                return -1;
            }
        }


}
