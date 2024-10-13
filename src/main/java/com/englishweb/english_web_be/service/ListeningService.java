package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.ListeningDTO;
import com.englishweb.english_web_be.model.Listening;
import com.englishweb.english_web_be.repository.ListeningRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ListeningService {
    ListeningRepository repository;

    public ListeningService(ListeningRepository repository) {
        this.repository = repository;
    }

    Page<ListeningDTO> retrieveListeningsByPage(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Listening> entityPage = repository.findAllListening(pageable);
        return entityPage.map(this::convertToDTO);
    }

    Page<ListeningDTO> retrieveListeningsByPage(int page, int size, Sort sort){
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Listening> entityPage = repository.findAllListening(pageable);
        return entityPage.map(this::convertToDTO);
    }

    private ListeningDTO convertToDTO(Listening entity){
        ListeningDTO dto = new ListeningDTO();
        dto.setId(entity.getId());
        dto.setSerial(entity.getSerial());
        dto.setImage(entity.getImage());
        dto.setDescription(entity.getDescription());
        dto.setTitle(entity.getTitle());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
