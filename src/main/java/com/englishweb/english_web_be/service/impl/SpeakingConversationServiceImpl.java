package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.SpeakingConversationDTO;
import com.englishweb.english_web_be.dto.response.SpeakingConversationResponse;
import com.englishweb.english_web_be.model.SpeakingConversation;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.SpeakingConversationRepository;
import com.englishweb.english_web_be.service.SpeakingConversationService;
import com.englishweb.english_web_be.service.TextToSpeechService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.InputStreamResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@Slf4j
public class SpeakingConversationServiceImpl extends BaseServiceImpl<SpeakingConversation, SpeakingConversationDTO,
        SpeakingConversationRepository> implements SpeakingConversationService {

    private final SpeakingServiceImpl speakingService;
    private final TextToSpeechService textToSpeechService;
    private final FirebaseStorageServiceImpl firebaseStorageService;

    public SpeakingConversationServiceImpl(SpeakingConversationRepository repository,
                                           @Lazy SpeakingServiceImpl speakingService, TextToSpeechService textToSpeechService, FirebaseStorageServiceImpl firebaseStorageService) {
        super(repository);
        this.speakingService = speakingService;
        this.textToSpeechService = textToSpeechService;
        this.firebaseStorageService = firebaseStorageService;
    }

    @Override
    public List<SpeakingConversationDTO> findBySpeakingId(String speakingId) {
        speakingService.isExist(speakingId);
        List<SpeakingConversation> entityList = repository.findAllBySpeaking_Id(speakingId);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<SpeakingConversationDTO> findBySpeakingIdAndStatus(String speakingId, StatusEnum status) {
        if(status == null)
            return findBySpeakingId(speakingId);
        List<SpeakingConversation> entityList = repository.findAllBySpeaking_IdAndStatus(speakingId, status);
        return entityList.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public SpeakingConversationResponse saveAudio(SpeakingConversationDTO dto) {
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

    private byte[] inputStreamToByteArray(InputStreamResource audioResource) throws IOException {
        try (InputStream inputStream = audioResource.getInputStream()) {
            return inputStream.readAllBytes();
        }
    }

    @Override
    public void deleteAudio(SpeakingConversationDTO dto) {
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

    @Override
    public String getAudio(SpeakingConversationDTO dto) {
        try {
            String path = "study/speaking/conversation/" + dto.getSpeakingId();
            String fileName = dto.getId();

            String audioUrl = "https://firebasestorage.googleapis.com/v0/b/englishweb-5a6ce.appspot.com/o/"
                    + path.replace("/", "%2F") + "%2F" + fileName + "?alt=media";

            log.info("Audio URL retrieved successfully for SpeakingConversation with ID: {}", dto.getId());
            return audioUrl;
        } catch (Exception e) {
            log.error("Error occurred while retrieving audio for SpeakingConversation with ID: {}", dto.getId(), e);
            return null;
        }
    }


    @Override
    public SpeakingConversationDTO create(SpeakingConversationDTO dto) {
        if (isSerialUnique(dto.getSpeakingId(), dto.getSerial(), null)) {
            throw new DataIntegrityViolationException("Serial " + dto.getSerial() + " must be unique for Speaking ID: " + dto.getSpeakingId());
        }
        return super.create(dto);
    }

    @Override
    public SpeakingConversationDTO update(SpeakingConversationDTO dto, String id) {
        if (isSerialUnique(dto.getSpeakingId(), dto.getSerial(), id)) {
            throw new DataIntegrityViolationException("Serial " + dto.getSerial() + " must be unique for Speaking ID: " + dto.getSpeakingId());
        }
        return super.update(dto, id);
    }

    private boolean isSerialUnique(String speakingId, Integer serial, String excludeId) {
        List<SpeakingConversation> entityList = repository.findAllBySpeaking_Id(speakingId);
        return entityList.stream()
                .filter(entity -> !entity.getId().equals(excludeId))
                .anyMatch(entity -> entity.getSerial() == serial);
    }

    @Override
    public void delete(String id) {
        SpeakingConversationDTO dto = super.findById(id);
        this.deleteAudio(dto);
        super.delete(id);
    }

    @Override
    protected SpeakingConversationDTO convertToDTO(SpeakingConversation entity) {
        SpeakingConversationDTO dto = new SpeakingConversationDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setContent(entity.getContent());
        dto.setSerial(entity.getSerial());
        dto.setStatus(entity.getStatus());
        dto.setSpeakingId(entity.getSpeaking().getId());
        return dto;
    }

    @Override
    protected SpeakingConversation convertToEntity(SpeakingConversationDTO dto) {
        SpeakingConversation entity = new SpeakingConversation();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setContent(dto.getContent());
        entity.setSerial(dto.getSerial());
        entity.setStatus(dto.getStatus());
        entity.setSpeaking(speakingService.convertToEntity(speakingService.findById(dto.getSpeakingId())));
        return entity;
    }
}
