package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.UserDTO;
import com.englishweb.english_web_be.exception.ResourceIsExistException;
import com.englishweb.english_web_be.exception.ResourceNotFoundException;
import com.englishweb.english_web_be.exception.UserNotFoundException;
import com.englishweb.english_web_be.model.User;
import com.englishweb.english_web_be.modelenum.LevelEnum;
import com.englishweb.english_web_be.modelenum.RoleEnum;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.UserRepository;
import com.englishweb.english_web_be.service.EmailService;
import com.englishweb.english_web_be.service.UserService;
import com.englishweb.english_web_be.util.UserSpecification;
import com.englishweb.english_web_be.util.ValidationUtils;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.regex.Pattern;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl extends BaseServiceImpl<User, UserDTO, UserRepository> implements UserService {

    EmailService emailService;
    PasswordEncoder passwordEncoder;
    private Map<String, OtpData> otpCache = new HashMap<>();
    private Map<String, Boolean> otpVerifiedCache = new HashMap<>();
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository repository,
                           EmailService emailService,
                           PasswordEncoder passwordEncoder, UserRepository userRepository) {
        super(repository);
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    private class OtpData {
        String otp;
        long timestamp;

        OtpData(String otp, long timestamp) {
            this.otp = otp;
            this.timestamp = timestamp;
        }
    }

    @Override
    public Page<UserDTO> findByRole(RoleEnum role, int page, int size, String sortBy, String sortDir, Class<UserDTO> userResponseDTOClass) {
        ValidationUtils.getInstance().validatePageRequestParam(page, size, sortBy, userResponseDTOClass);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> entityPage = repository.findByRoleEnum(role, pageable);
        return entityPage.map(this::convertToDTO);
    }

    public Page<UserDTO> findTeachersBySpecification(String name, LocalDate searchStartDate, LocalDate searchEndDate, LevelEnum level, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Specification<User> spec = Specification.where(UserSpecification.hasRole(RoleEnum.TEACHER))
                .and(UserSpecification.hasName(name))
                .and(UserSpecification.hasLevel(level))
                .and(UserSpecification.hasDateRange(searchStartDate, searchEndDate)); // Sử dụng bộ lọc ngày

        Page<User> userPage = userRepository.findAll(spec, pageable);
        return userPage.map(this::convertToDTO);
    }

    public Page<UserDTO> findStudentsBySpecification(String name, LocalDate searchStartDate, LocalDate searchEndDate, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Specification<User> spec = Specification.where(UserSpecification.hasRole(RoleEnum.STUDENT))
                .and(UserSpecification.hasName(name))
                .and(UserSpecification.hasDateRange(searchStartDate, searchEndDate));

        Page<User> userPage = userRepository.findAll(spec, pageable);
        return userPage.map(this::convertToDTO);
    }

    @Override
    public UserDTO createStudent(UserDTO dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new ResourceIsExistException("Email already exists. Please use another email.");
        }
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        dto.setStatus(StatusEnum.ACTIVE);
        dto.setRole(RoleEnum.STUDENT);
        return create(dto);
    }

    @Override
    public UserDTO createTeacher(UserDTO dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new ResourceIsExistException("Email already exists. Please use another email.");
        }

        String rawPassword = generatePassword(12);
        dto.setPassword(passwordEncoder.encode(rawPassword));
        dto.setStatus(StatusEnum.ACTIVE);
        dto.setRole(RoleEnum.TEACHER);

        UserDTO createdTeacher = create(dto);
        emailService.sendPasswordByEmail(dto.getEmail(), rawPassword);

        return createdTeacher;
    }

    @Override
    public UserDTO getInfor() {
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user = repository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found."));
        return convertToDTO(user);
    }

    @Override
    public UserDTO update(UserDTO dto, String id) {
        User existingUser = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found."));

        BiConsumer<String, Consumer<String>> updateIfPresent = (newValue, setter) -> {
            if (newValue != null && !newValue.trim().isEmpty()) {
                setter.accept(newValue);
            }
        };

        updateIfPresent.accept(dto.getName(), existingUser::setName);
        updateIfPresent.accept(dto.getEmail(), existingUser::setEmail);
        updateIfPresent.accept(dto.getPassword(), password -> existingUser.setPassword(passwordEncoder.encode(password)));
        updateIfPresent.accept(dto.getAvatar(), existingUser::setAvatar);
        updateIfPresent.accept(dto.getContentMotivation(), existingUser::setContentMotivation);

        if (dto.getStatus() != null) {
            existingUser.setStatus(dto.getStatus());
        }
        if (dto.getRole() != null) {
            existingUser.setRoleEnum(dto.getRole());
        }
        if (dto.getLevel() != null) {
            existingUser.setLevelEnum(dto.getLevel());
        }
        if (dto.getStartDate() != null) {
            existingUser.setStartDate(dto.getStartDate());
        }
        if (StatusEnum.ACTIVE.equals(dto.getStatus())) {
            existingUser.setEndDate(null);
        } else if (dto.getEndDate() != null) {
            existingUser.setEndDate(dto.getEndDate());
        }

        return convertToDTO(repository.save(existingUser));
    }

    public void changePassword(String id, String oldPassword, String newPassword) {
        if (oldPassword == null || newPassword == null) {
            throw new IllegalArgumentException("Both old and new passwords must be provided.");
        }

        User existingUser = repository.findById(id)

                .orElseThrow(() -> new UserNotFoundException("User not found."));


        if (!passwordEncoder.matches(oldPassword, existingUser.getPassword())) {
            throw new RuntimeException("Old password is incorrect.");
        }

        existingUser.setPassword(passwordEncoder.encode(newPassword));
        repository.save(existingUser);
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

    public void sendOtpForPasswordReset(String email) {
        if (email.trim().isEmpty()) {
            throw new RuntimeException("Email cannot be empty.");
        }
        Optional<User> user = repository.findByEmail(email);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("Email does not exist.");
        }

        String otp = generateOtp();
        long timestamp = System.currentTimeMillis(); // Lấy thời gian hiện tại
        otpCache.put(email, new OtpData(otp, timestamp)); // Lưu OTP và thời gian tạo
        emailService.sendOtpByEmail(email, otp);
    }

    private String generateOtp() {
        return String.format("%06d", new SecureRandom().nextInt(1000000));
    }

    public boolean verifyOtp(String email, String inputOtp) {
        OtpData otpData = otpCache.get(email.trim());
        if (otpData == null) {
            return false;
        }

        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - otpData.timestamp;
        if (elapsedTime > 60 * 1000) {
            otpCache.remove(email.trim());
            return false;
        }

        if (!otpData.otp.trim().equals(inputOtp.trim())) {
            return false;
        }

        otpCache.remove(email.trim());
        otpVerifiedCache.put(email.trim(), true);
        return true;
    }

    public void resetPassword(String email, String newPassword, String confirmPassword) {
        if (otpVerifiedCache.get(email.trim()) == null || !otpVerifiedCache.get(email.trim())) {
            throw new RuntimeException("You need to verify the OTP code before changing your password.");
        }
        if (!newPassword.equals(confirmPassword)) {
            throw new RuntimeException("New password and confirm password do not match.");
        }
        Optional<User> userOptional = repository.findByEmail(email);
        User user = userOptional.get();
        String hashedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(hashedPassword);
        repository.save(user);
        otpVerifiedCache.remove(email.trim());
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
        entity.setStatus(dto.getStatus());
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
        dto.setAvatar(entity.getAvatar());
        dto.setContentMotivation(entity.getContentMotivation());
        dto.setRole(entity.getRoleEnum());
        dto.setStatus(entity.getStatus());
        dto.setLevel(entity.getLevelEnum());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        return dto;
    }
}
