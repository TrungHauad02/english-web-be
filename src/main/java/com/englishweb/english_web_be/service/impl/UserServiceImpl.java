package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.modelenum.RoleEnum;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.englishweb.english_web_be.dto.UserDTO;
import com.englishweb.english_web_be.model.User;
import com.englishweb.english_web_be.repository.UserRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class UserServiceImpl extends BaseServiceImpl<User, UserDTO, UserRepository> implements UserService {
    UserRepository repository;
    PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        super(repository);
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO createStudent(UserDTO dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists. Please use another email.");
        }
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        dto.setStatus(StatusEnum.ACTIVE);
        dto.setRole(RoleEnum.STUDENT);
        return create(dto);
    }

    public UserDTO createTeacher(UserDTO dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists. Please use another email.");
        }

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        dto.setStatus(StatusEnum.ACTIVE);
        dto.setRole(RoleEnum.TEACHER);
        return create(dto);
    }

    public UserDTO deleteUser(String id) {
        Optional<User> userOptional = repository.findById(id);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found.");
        }

        User user = userOptional.get();

        user.setStatusEnum(StatusEnum.INACTIVE);
        user.setEndDate(LocalDate.now());

        repository.save(user);

        return convertToDTO(user);
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
        entity.setLevelEnum(dto.getLevel());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
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
        dto.setStartDate();
        dto.setEndDate(entity.getEndDate());
        return dto;
    }
}
