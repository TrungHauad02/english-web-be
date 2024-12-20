package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.SpeakingConversationDTO;
import com.englishweb.english_web_be.dto.response.SpeakingConversationResponse;
import com.englishweb.english_web_be.model.SpeakingConversation;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.SpeakingConversationRepository;
import com.englishweb.english_web_be.service.SpeakingConversationService;
import com.englishweb.english_web_be.service.TextToSpeechService;
import com.englishweb.english_web_be.util.SpeakingConversationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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
        return SpeakingConversationUtils.saveAudio(textToSpeechService, firebaseStorageService, dto);
    }

    @Override
    public void deleteAudio(SpeakingConversationDTO dto) {
        SpeakingConversationUtils.deleteAudio(firebaseStorageService, dto);
    }

    @Override
    public String getAudio(SpeakingConversationDTO dto) {
        return SpeakingConversationUtils.getAudioUrl(dto);
    }

    @Override
    public SpeakingConversationDTO create(SpeakingConversationDTO dto) {
        if (SpeakingConversationUtils.isSerialUnique(dto.getSpeakingId(), dto.getSerial(), null, repository)) {
            throw new DataIntegrityViolationException("Serial " + dto.getSerial() + " must be unique for Speaking ID: " + dto.getSpeakingId());
        }
        SpeakingConversation entity = convertToEntity(dto);
        entity.setId(null);
        log.info("Saving SpeakingConversation: {}", entity);

        SpeakingConversationDTO dtoCreated = convertToDTO(repository.save(entity));
        log.info("Saved successfully speaking conversation: {}", dtoCreated.getId());

        return dtoCreated;
    }

    @Override
    public SpeakingConversationDTO update(SpeakingConversationDTO dto, String id) {
        if (SpeakingConversationUtils.isSerialUnique(dto.getSpeakingId(), dto.getSerial(), id, repository)) {
            throw new DataIntegrityViolationException("Serial " + dto.getSerial() + " must be unique for Speaking ID: " + dto.getSpeakingId());
        }

        SpeakingConversation entity = convertToEntity(dto);
        entity.setId(id);

        log.info("Updating SpeakingConversation with ID {}: {}", id, entity);
        SpeakingConversationDTO updatedDto = convertToDTO(repository.save(entity));
        log.info("Updated successfully speaking conversation with ID {}: {}", id, updatedDto);
        return updatedDto;
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

