package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.WritingDTO;
import com.englishweb.english_web_be.dto.request.WritingRequestDTO;
import com.englishweb.english_web_be.dto.response.WritingResponseDTO;
import com.englishweb.english_web_be.mapper.WritingMapper;
import com.englishweb.english_web_be.model.Writing;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.WritingRepository;
import com.englishweb.english_web_be.service.WritingService;
import com.englishweb.english_web_be.util.ValidationUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class WritingServiceImpl extends BaseServiceImpl<Writing, WritingDTO, WritingRequestDTO,
        WritingResponseDTO, WritingMapper, WritingRepository> implements WritingService {

    public WritingServiceImpl(WritingRepository repository, @Lazy WritingMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public Page<WritingResponseDTO> findWritingWithStatusAndPagingAndSorting(StatusEnum status, int page, int size, String sortBy, String sortDir, Class<WritingResponseDTO> dtoClass) {
        if(status == null)
            return super.findByPage(page, size, sortBy, sortDir, dtoClass);

        ValidationUtils.getInstance().validatePageRequestParam(page, size, sortBy, dtoClass);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAllWritingByStatus(status, pageable)
                .map(this::convertToDTO)
                .map(mapper::mapToResponseDTO);
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
