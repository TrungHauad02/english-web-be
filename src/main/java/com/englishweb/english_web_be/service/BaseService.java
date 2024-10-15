package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.model.BaseEntity;
import com.englishweb.english_web_be.util.ValidationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<Entity extends BaseEntity, DTO extends BaseDTO, R extends JpaRepository<Entity, String>> {
    protected R repository;
    public BaseService(R repository) {
        this.repository = repository;
    }

    public Page<DTO> findByPage(int page, int size, String sortBy, String sortDir, Class<DTO> dtoClass) {
        ValidationUtils.getInstance().validatePageRequestParam(page, size, sortBy, dtoClass);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Entity> entityPage = repository.findAll(pageable);
        return entityPage.map(this::convertToDTO);
    }

    public DTO findById(String id) {
        ValidationUtils.getInstance().validateExistId(repository, id);
        return convertToDTO(repository.findById(id).get());
    }

    public DTO create(DTO dto) {
        return convertToDTO(repository.save(convertToEntity(dto)));
    }

    public DTO update(DTO dto) {
        ValidationUtils.getInstance().validateExistId(repository, dto.getId());
        Entity entity = convertToEntity(dto);
        entity.setId(dto.getId());
        return convertToDTO(repository.save(entity));
    }

    public void delete(String id) {
        ValidationUtils.getInstance().validateExistId(repository, id);
        repository.deleteById(id);
    }

    protected abstract Entity convertToEntity(DTO dto);
    protected abstract DTO convertToDTO(Entity entity);
}
