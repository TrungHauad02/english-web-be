package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.BaseDTO;
import com.englishweb.english_web_be.model.BaseEntity;
import com.englishweb.english_web_be.service.BaseService;
import com.englishweb.english_web_be.util.ValidationUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

@AllArgsConstructor
@Slf4j
public abstract class BaseServiceImpl<Entity extends BaseEntity, DTO extends BaseDTO,
                R extends JpaRepository<Entity, String>> implements BaseService<DTO> {
    protected R repository;

    public Page<DTO> findByPage(int page, int size, String sortBy, String sortDir, Class<DTO> dtoClass) {
        log.info("Finding by page: page={}, size={}, sortBy={}, sortDir={}, dtoClass={}", page, size, sortBy, sortDir, dtoClass.getName());
        ValidationUtils.getInstance().validatePageRequestParam(page, size, sortBy, dtoClass);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Entity> entityPage = repository.findAll(pageable);
        Page<DTO> dtoPage = entityPage.map(this::convertToDTO);

        log.info("Found {} records on page {}", dtoPage.getTotalElements(), page);
        return dtoPage;
    }

    public DTO findById(String id) {
        log.info("Finding by ID: {}", id);
        isExist(id);
        DTO dto = convertToDTO(repository.findById(id).get());

        log.info("Found entity with ID {}: {}", id, dto);
        return dto;
    }

    public DTO create(DTO dto) {
        log.info("Creating new entity with DTO: {}", dto);
        Entity entity = convertToEntity(dto);
        entity.setId(null);
        DTO savedDTO = convertToDTO(repository.save(entity));

        log.info("Created entity with ID {}: {}", savedDTO.getId(), savedDTO);
        return savedDTO;
    }

    public DTO update(DTO dto, String id) {
        log.info("Updating entity with ID {} using DTO: {}", id, dto);

        isExist(id);
        dto.setId(id);

        Entity updatedEntity = repository.save(convertToEntity(dto));
        DTO updatedDto = convertToDTO(updatedEntity);

        log.info("Updated entity with ID {}: {}", id, updatedDto);
        return updatedDto;
    }

    public void delete(String id) {
        log.info("Deleting entity with ID: {}", id);

        isExist(id);
        repository.deleteById(id);

        log.info("Deleted entity with ID: {}", id);
    }

    public void isExist(String id) {
        log.info("Checking existence for entity ID: {}", id);
        ValidationUtils.getInstance().validateExistId(repository, id);
        log.info("Entity with ID {} exists", id);
    }

    protected abstract Entity convertToEntity(DTO dto);

    protected abstract DTO convertToDTO(Entity entity);
}
