package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.model.BaseEntity;
import com.englishweb.english_web_be.service.BaseService;
import com.englishweb.english_web_be.util.ValidationUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

@AllArgsConstructor
public abstract class BaseServiceImpl<Entity extends BaseEntity, DTO extends BaseDTO,
                R extends JpaRepository<Entity, String>> implements BaseService<DTO> {
    protected R repository;

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
        isExist(id);
        return convertToDTO(repository.findById(id).get());
    }

    public DTO create(DTO dto) {
        Entity entity = convertToEntity(dto);
        entity.setId(null);
        return convertToDTO(repository.save(entity));
    }

    public DTO update(DTO dto, String id) {
        isExist(id);
        dto.setId(id);
        return convertToDTO(repository.save(convertToEntity(dto)));
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
