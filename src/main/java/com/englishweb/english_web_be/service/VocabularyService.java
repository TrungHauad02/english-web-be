package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.VocabularyDTO;
import com.englishweb.english_web_be.model.Vocabulary;
import com.englishweb.english_web_be.repository.VocabularyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VocabularyService {
    VocabularyRepository repository;
    public VocabularyService(VocabularyRepository repository){
        this.repository = repository;
    }

    public Page<VocabularyDTO> retrieveVocabsInTopicByPage(int page, int pageSize, String topicId){
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Vocabulary> entityPage = repository.retrieveVocabsInTopicByPage(pageable, topicId);
        return entityPage.map(this::convertToDTO);
    }

    public VocabularyDTO convertToDTO(Vocabulary vocab){
        VocabularyDTO dto = new VocabularyDTO();
        dto.setId(vocab.getId());
        dto.setWord(vocab.getWord());
        dto.setMeaning(vocab.getMeaning());
        dto.setWordType(vocab.getWordType());
        dto.setPhonetic(vocab.getPhonetic());
        dto.setStatus(vocab.getStatus());
        dto.setImage(vocab.getImage());
        dto.setExample(vocab.getExample());
        return dto;
    }
}
