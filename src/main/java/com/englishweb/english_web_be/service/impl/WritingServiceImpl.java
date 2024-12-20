package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.WritingDTO;
import com.englishweb.english_web_be.model.Writing;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.WritingRepository;
import com.englishweb.english_web_be.repository.specifications.LessonSpecification;
import com.englishweb.english_web_be.service.WritingService;
import com.englishweb.english_web_be.util.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WritingServiceImpl extends BaseServiceImpl<Writing, WritingDTO, WritingRepository> implements WritingService {

    public WritingServiceImpl(WritingRepository repository) {
        super(repository);
    }

    @Override
    public Page<WritingDTO> findWithPagingSortingSearching(String title, StatusEnum status, int page, int size, String sortBy, String sortDir, Class<WritingDTO> dtoClass) {
        ValidationUtils.getInstance().validatePageRequestParam(page, size, sortBy, dtoClass);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Specification<Writing> specTitle = Specification.where(title != null ? LessonSpecification.byTitle(title) : null);
        Specification<Writing> spec= specTitle.and(Specification.where(status != null ? LessonSpecification.hasStatus(status): null));
        log.info("Find Writing page with searching: title: {}, status: {}, pageable: {}", title, status, pageable);
        Page<WritingDTO> result = repository.findAll(spec, pageable).map(this::convertToDTO);
        log.info("Find Writing page with searching successfully: {} record found.", result.getNumberOfElements());
        return result;
    }

    @Override
    protected WritingDTO convertToDTO(Writing entity) {
        WritingDTO dto = new WritingDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setImage(entity.getImage());
        dto.setSerial(entity.getSerial());
        dto.setTitle(entity.getTitle());
        dto.setStatus(entity.getStatus());
        dto.setTopic(entity.getTopic());
        return dto;
    }

    @Override
    protected Writing convertToEntity(WritingDTO dto) {
        Writing entity = new Writing();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
        entity.setSerial(dto.getSerial());
        entity.setTitle(dto.getTitle());
        entity.setStatus(dto.getStatus());
        entity.setTopic(dto.getTopic());
        return entity;
    }
}
