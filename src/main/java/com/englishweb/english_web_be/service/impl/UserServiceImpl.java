package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.modelenum.RoleEnum;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.service.UserService;
import com.englishweb.english_web_be.util.ValidationUtils;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.englishweb.english_web_be.dto.UserDTO;
import com.englishweb.english_web_be.model.User;
import com.englishweb.english_web_be.repository.UserRepository;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class UserServiceImpl extends BaseServiceImpl<User, UserDTO, UserRepository> implements UserService {
    private EmailService emailService;
    PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, EmailService emailService, PasswordEncoder passwordEncoder) {
        super(repository);
        this.emailService = emailService;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<UserDTO> findByRole(int page, int size, String sortBy, String sortDir, RoleEnum role, Class<UserDTO> dtoClass) {
        ValidationUtils.getInstance().validatePageRequestParam(page, size, sortBy, dtoClass);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> entityPage = repository.findByRoleEnum(role, pageable);
        return entityPage.map(this::convertToDTO);
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

        String rawPassword = generatePassword(12);
        dto.setPassword(passwordEncoder.encode(rawPassword));

        dto.setStatus(StatusEnum.ACTIVE);
        dto.setRole(RoleEnum.TEACHER);

        UserDTO createdTeacher = create(dto);

        emailService.sendPasswordByEmail(dto.getEmail(), rawPassword);

        return createdTeacher;
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

    public UserDTO getInfor(){
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();

        User user = repository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found."));

        return convertToDTO(user);
    }

    @Override
    public UserDTO update(UserDTO dto, String id) {
        User existingUser = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found."));

        if (dto.getPassword() != null && !dto.getPassword().equals(existingUser.getPassword())) {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        } else {
            dto.setPassword(existingUser.getPassword());
        }

        if (dto.getEmail() == null) {
            dto.setEmail(existingUser.getEmail());
        }

        if(dto.getName() == null){
            dto.setName(existingUser.getName());
        }

        return super.update(dto, id);
    }

    private String generatePassword(int length) {
        final SecureRandom RANDOM = new SecureRandom();
        final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
        final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String DIGITS = "0123456789";
        final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?/{}~|";
        final String ALL_CHARACTERS = LOWER_CASE + UPPER_CASE + DIGITS + SPECIAL_CHARACTERS;

        StringBuilder password = new StringBuilder(length);

        password.append(LOWER_CASE.charAt(RANDOM.nextInt(LOWER_CASE.length())));
        password.append(UPPER_CASE.charAt(RANDOM.nextInt(UPPER_CASE.length())));
        password.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARACTERS.charAt(RANDOM.nextInt(SPECIAL_CHARACTERS.length())));

        for (int i = 4; i < length; i++) {
            password.append(ALL_CHARACTERS.charAt(RANDOM.nextInt(ALL_CHARACTERS.length())));
        }

        char[] characters = password.toString().toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i + 1);
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }

        return new String(characters);
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
