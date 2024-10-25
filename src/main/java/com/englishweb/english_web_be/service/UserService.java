package com.englishweb.english_web_be.service;

import org.springframework.stereotype.Service;

import com.englishweb.english_web_be.dto.UserDTO;
import com.englishweb.english_web_be.model.User;
import com.englishweb.english_web_be.repository.UserRepository;

@Service
public class UserService extends BaseService<User, UserDTO, UserRepository> {
    public UserService(UserRepository repository) {
        super(repository);
    }

    @Override
    protected User convertToEntity(UserDTO dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setAvatar(dto.getAvatar());
        entity.setContentMotivation(dto.getContentMotivation());
        entity.setRoleEnum(dto.getRole());
        entity.setStatusEnum(dto.getStatus());
        return entity;
    }

    @Override
    protected UserDTO convertToDTO(User entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setAvatar(entity.getAvatar());
        dto.setContentMotivation(entity.getContentMotivation());
        dto.setRole(entity.getRoleEnum());
        dto.setStatus(entity.getStatusEnum());
        dto.setLevel(entity.getLevelEnum());
        return dto;
    }
}
