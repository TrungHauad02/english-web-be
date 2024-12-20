package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.ListenAndWriteAWordDTO;
import com.englishweb.english_web_be.dto.ListeningDTO;
import com.englishweb.english_web_be.dto.ListeningQuestionDTO;
import com.englishweb.english_web_be.model.Listening;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.ListeningRepository;
import com.englishweb.english_web_be.repository.specifications.LessonSpecification;
import com.englishweb.english_web_be.service.ListeningService;
import com.englishweb.english_web_be.util.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ListeningServiceImpl extends BaseServiceImpl<Listening, ListeningDTO, ListeningRepository>
        implements ListeningService {

    private final ListenAndWriteAWordServiceImpl listenAndWriteAWordService;
    private final ListeningQuestionServiceImpl listeningQuestionService;

    public ListeningServiceImpl(ListeningRepository repository,
                                ListenAndWriteAWordServiceImpl listenAndWriteAWordService,
                                ListeningQuestionServiceImpl listeningQuestionService) {
        super(repository);
        this.listenAndWriteAWordService = listenAndWriteAWordService;
        this.listeningQuestionService = listeningQuestionService;
    }

    @Override
    public Page<ListeningDTO> findWithPagingSortingSearching(
            String title, StatusEnum status, int page, int size, String sortBy, String sortDir,
            Class<ListeningDTO> dtoClass) {
        ValidationUtils.getInstance().validatePageRequestParam(page, size, sortBy, dtoClass);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Specification<Listening> specTitle = Specification.where(title != null ? LessonSpecification.byTitle(title) : null);
        Specification<Listening> spec = specTitle.and(Specification.where(status != null ? LessonSpecification.hasStatus(status) : null));
        log.info("Find Listening page with searching: title: {}, status: {}, pageable: {}", title, status, pageable);
        Page<ListeningDTO> result = repository.findAll(spec, pageable).map(this::convertToDTO);
        log.info("Find Listening page with searching successfully: {} record found.", result.getNumberOfElements());
        return result;
    }

    @Override
    public void delete(String id) {
        isExist(id);
        List<ListeningQuestionDTO> questionDTOList = listeningQuestionService.findByListeningId(id);
        for (ListeningQuestionDTO questionDTO : questionDTOList) {
            listeningQuestionService.delete(questionDTO.getId());
        }
        List<ListenAndWriteAWordDTO> listenAndWriteAWordDTOList = listenAndWriteAWordService.findByListeningId(id);
        for (ListenAndWriteAWordDTO listenAndWriteAWordDTO : listenAndWriteAWordDTOList) {
            listenAndWriteAWordService.delete(listenAndWriteAWordDTO.getId());
        }
        super.delete(id);
    }

    @Override
    protected ListeningDTO convertToDTO(Listening entity) {
        ListeningDTO dto = new ListeningDTO();
        dto.setId(entity.getId());
        dto.setSerial(entity.getSerial());
        dto.setImage(entity.getImage());
        dto.setDescription(entity.getDescription());
        dto.setTitle(entity.getTitle());
        dto.setStatus(entity.getStatus());
        dto.setAudioUrl(entity.getAudioUrl());
        return dto;
    }

    @Override
    protected Listening convertToEntity(ListeningDTO dto) {
        Listening entity = new Listening();
        entity.setId(dto.getId());
        entity.setSerial(dto.getSerial());
        entity.setImage(dto.getImage());
        entity.setDescription(dto.getDescription());
        entity.setTitle(dto.getTitle());
        entity.setStatus(dto.getStatus());
        entity.setAudioUrl(dto.getAudioUrl());
        return entity;
    }
}
