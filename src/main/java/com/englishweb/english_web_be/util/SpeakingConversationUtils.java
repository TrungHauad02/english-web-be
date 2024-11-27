package com.englishweb.english_web_be.util;

import com.englishweb.english_web_be.dto.SpeakingConversationDTO;
import com.englishweb.english_web_be.dto.request.PostRequest;
import com.englishweb.english_web_be.dto.response.PostResponse;
import com.englishweb.english_web_be.dto.response.SpeakingConversationResponse;
import com.englishweb.english_web_be.model.SpeakingConversation;
import com.englishweb.english_web_be.repository.SpeakingConversationRepository;
import com.englishweb.english_web_be.service.impl.FirebaseStorageServiceImpl;
import com.englishweb.english_web_be.service.TextToSpeechService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.dao.DataIntegrityViolationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
public class SpeakingConversationUtils {

    // ======= POST-RELATED METHODS =======

    public static String addPostForSpeaking(ScoreSpeakingServiceImpl scoreSpeakingServiceImpl, String title, String content) {
        PostResponse postResponse = scoreSpeakingServiceImpl.addPost(
                PostRequest.builder()
                        .postTitle(title)
                        .postContent(content)
                        .postLanguageId("22")
                        .build()
        );

        if (postResponse != null) {
            log.info("Post created with ID: {}", postResponse.getPostId());
            return postResponse.getPostId();
        } else {
            log.warn("Failed to create post for title: {}", title);
            return "";
        }
    }

    public static void deletePostForSpeaking(ScoreSpeakingServiceImpl scoreSpeakingServiceImpl, SpeakingConversation entity) {
        if (entity.getPostId() != null) {
            scoreSpeakingServiceImpl.deletePost(entity.getPostId());
            log.info("Deleted post with ID: {}", entity.getPostId());
        }
    }

    // ======= AUDIO-RELATED METHODS =======

    public static SpeakingConversationResponse saveAudio(TextToSpeechService textToSpeechService,
                                                         FirebaseStorageServiceImpl firebaseStorageService,
                                                         SpeakingConversationDTO dto) {
        try {
            InputStreamResource audioResource = textToSpeechService.convertTextToSpeech(dto.getContent(), dto.getName());
            byte[] audioBytes = inputStreamToByteArray(audioResource);

            String path = "study/speaking/conversation/" + dto.getSpeakingId();
            String fileName = dto.getId();
            String mimeType = "audio/mp3";
            String audioUrl = firebaseStorageService.uploadFile(path, fileName, audioBytes, mimeType, FirebaseStorageServiceImpl.RandomName.NO);

            return SpeakingConversationResponse.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .serial(dto.getSerial())
                    .content(dto.getContent())
                    .status(dto.getStatus())
                    .speakingId(dto.getSpeakingId())
                    .audioUrl(audioUrl)
                    .build();
        } catch (IOException | InterruptedException e) {
            log.error("Error occurred while processing audio for SpeakingConversation with ID: {}", dto.getId(), e);
            return null;
        }
    }

    public static void deleteAudio(FirebaseStorageServiceImpl firebaseStorageService, SpeakingConversationDTO dto) {
        try {
            String path = "study/speaking/conversation/" + dto.getSpeakingId();
            String fileName = dto.getId();

            String fileUrl = "https://firebasestorage.googleapis.com/v0/b/englishweb-5a6ce.appspot.com/o/"
                    + path.replace("/", "%2F") + "%2F" + fileName + "?alt=media";

            firebaseStorageService.deleteFile(fileUrl);

            log.info("Successfully deleted audio file for SpeakingConversation with ID: {}", dto.getId());
        } catch (IOException e) {
            log.error("Error occurred while deleting audio for SpeakingConversation with ID: {}", dto.getId(), e);
        }
    }

    public static String getAudioUrl(SpeakingConversationDTO dto) {
        String path = "study/speaking/conversation/" + dto.getSpeakingId();
        String fileName = dto.getId();

        return "https://firebasestorage.googleapis.com/v0/b/englishweb-5a6ce.appspot.com/o/"
                + path.replace("/", "%2F") + "%2F" + fileName + "?alt=media";
    }

    // ======= UTILITY METHODS =======

    public static byte[] inputStreamToByteArray(InputStreamResource audioResource) throws IOException {
        try (InputStream inputStream = audioResource.getInputStream()) {
            return inputStream.readAllBytes();
        }
    }

    public static boolean shouldAddNewPost(SpeakingConversationDTO dto, SpeakingConversationRepository repository) {
        SpeakingConversation existingEntity = repository.findById(dto.getId())
                .orElseThrow(() -> new DataIntegrityViolationException("Entity not found for ID: " + dto.getId()));
        return !dto.getContent().equals(existingEntity.getContent());
    }

    public static boolean isSerialUnique(String speakingId, Integer serial, String excludeId, SpeakingConversationRepository repository) {
        List<SpeakingConversation> entityList = repository.findAllBySpeaking_Id(speakingId);
        return entityList.stream()
                .filter(entity -> !entity.getId().equals(excludeId))
                .anyMatch(entity -> entity.getSerial() == serial);
    }
}
