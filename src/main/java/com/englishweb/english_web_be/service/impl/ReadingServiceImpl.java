package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.ReadingDTO;
import com.englishweb.english_web_be.dto.ReadingQuestionDTO;
import com.englishweb.english_web_be.model.Reading;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.ReadingRepository;
import com.englishweb.english_web_be.repository.specifications.LessonSpecification;
import com.englishweb.english_web_be.service.ReadingQuestionService;
import com.englishweb.english_web_be.service.ReadingService;
import com.englishweb.english_web_be.util.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReadingServiceImpl extends BaseServiceImpl<Reading, ReadingDTO, ReadingRepository>
        implements ReadingService {

    private final ReadingQuestionService readingQuestionService;

    public ReadingServiceImpl(ReadingRepository repository,
                              ReadingQuestionService readingQuestionService) {
        super(repository);
        this.readingQuestionService = readingQuestionService;
    }

    @Override
    public Page<ReadingDTO> findWithPagingSortingSearching(String title, StatusEnum status, int page, int size, String sortBy, String sortDir, Class<ReadingDTO> dtoClass) {
        ValidationUtils.getInstance().validatePageRequestParam(page, size, sortBy, dtoClass);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Specification<Reading> specTitle = Specification.where(title != null ? LessonSpecification.byTitle(title) : null);
        Specification<Reading> spec = specTitle.and(status != null ? LessonSpecification.hasStatus(status) : null);
        log.info("Find Reading page with searching: title: {}, status: {}, pageable: {}", title, status, pageable);
        Page<ReadingDTO> result = repository.findAll(spec, pageable).map(this::convertToDTO);
        log.info("Find Reading page with searching successfully: {} record found.", result.getNumberOfElements());
        return result;
    }

    @Override
    public void delete(String id) {
        List<ReadingQuestionDTO> dtoList = readingQuestionService.findAllByReadingId(id);
        for(ReadingQuestionDTO dto: dtoList){
            readingQuestionService.delete(dto.getId());
        }
        super.delete(id);
    }

    @Override
    protected Reading convertToEntity(ReadingDTO dto) {
        Reading entity = new Reading();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setFile(dto.getFile());
        entity.setSerial(dto.getSerial());
        entity.setImage(dto.getImage());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    protected ReadingDTO convertToDTO(Reading entity) {
        ReadingDTO dto = new ReadingDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setFile(entity.getFile());
        dto.setSerial(entity.getSerial());
        dto.setImage(entity.getImage());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
