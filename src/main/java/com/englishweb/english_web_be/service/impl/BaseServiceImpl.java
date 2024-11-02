package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.mapper.BaseMapper;
import com.englishweb.english_web_be.model.BaseEntity;
import com.englishweb.english_web_be.service.BaseService;
import com.englishweb.english_web_be.util.ValidationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseServiceImpl<Entity extends BaseEntity, DTO extends BaseDTO,
                RequestDTO extends BaseDTO, ResponseDTO extends BaseDTO,
                Mapper extends BaseMapper<DTO, RequestDTO, ResponseDTO>,
                R extends JpaRepository<Entity, String>> implements BaseService<RequestDTO, ResponseDTO> {
    protected R repository;
    protected Mapper mapper;
    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    public Page<ResponseDTO> findByPage(int page, int size, String sortBy, String sortDir, Class<ResponseDTO> dtoClass) {
        ValidationUtils.getInstance().validatePageRequestParam(page, size, sortBy, dtoClass);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Entity> entityPage = repository.findAll(pageable);
        return entityPage.map(this::convertToDTO).map(mapper::mapToResponseDTO);
    }

    public ResponseDTO findById(String id) {
        isExist(id);
        return mapper.mapToResponseDTO(convertToDTO(repository.findById(id).get()));
    }

    public ResponseDTO create(RequestDTO dto) {
        Entity entity = convertToEntity(mapper.mapToDTO(dto));
        entity.setId(null);
        return mapper.mapToResponseDTO(convertToDTO(repository.save(entity)));
    }

    public ResponseDTO update(RequestDTO dto, String id) {
        isExist(id);
        dto.setId(id);
        return mapper.mapToResponseDTO(convertToDTO(repository.save(convertToEntity(mapper.mapToDTO(dto)))));
    }

    public void delete(String id) {
        isExist(id);
        repository.deleteById(id);
    }

    public void isExist(String id) {
        ValidationUtils.getInstance().validateExistId(repository, id);
    }

    protected abstract Entity convertToEntity(DTO dto);

    protected abstract DTO convertToDTO(Entity entity);
}
