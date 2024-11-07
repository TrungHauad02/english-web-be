package com.englishweb.english_web_be.service.impl;

import com.englishweb.english_web_be.dto.UserDTO;
import com.englishweb.english_web_be.dto.request.UserRequestDTO;
import com.englishweb.english_web_be.dto.response.UserResponseDTO;
import com.englishweb.english_web_be.mapper.UserMapper;
import com.englishweb.english_web_be.model.User;
import com.englishweb.english_web_be.modelenum.RoleEnum;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.UserRepository;
import com.englishweb.english_web_be.service.EmailService;
import com.englishweb.english_web_be.service.UserService;
import com.englishweb.english_web_be.util.ValidationUtils;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl extends BaseServiceImpl<User, UserDTO, UserRequestDTO, UserResponseDTO, UserMapper, UserRepository> implements UserService {

    EmailService emailService;
    PasswordEncoder passwordEncoder;
    private Map<String, OtpData> otpCache = new HashMap<>();
    private Map<String, Boolean> otpVerifiedCache = new HashMap<>();

    public UserServiceImpl(UserRepository repository,
                           EmailService emailService,
                           PasswordEncoder passwordEncoder,
                           @Lazy UserMapper mapper) {
        super(repository, mapper);
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
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
    public Page<UserResponseDTO> findByRole(RoleEnum role, int page, int size, String sortBy, String sortDir, Class<UserResponseDTO> userResponseDTOClass) {
        ValidationUtils.getInstance().validatePageRequestParam(page, size, sortBy, userResponseDTOClass);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> entityPage = repository.findByRoleEnum(role, pageable);
        return entityPage.map(this::convertToDTO).map(mapper::mapToResponseDTO);
    }

    @Override
    public UserResponseDTO createStudent(UserRequestDTO dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists. Please use another email.");
        }
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        dto.setStatus(StatusEnum.ACTIVE);
        dto.setRole(RoleEnum.STUDENT);
        return create(dto);
    }

    @Override
    public UserResponseDTO createTeacher(UserRequestDTO dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists. Please use another email.");
        }

        String rawPassword = generatePassword(12);
        dto.setPassword(passwordEncoder.encode(rawPassword));
        dto.setStatus(StatusEnum.ACTIVE);
        dto.setRole(RoleEnum.TEACHER);

        UserResponseDTO createdTeacher = create(dto);
        emailService.sendPasswordByEmail(dto.getEmail(), rawPassword);

        return createdTeacher;
    }

    @Override
    public UserResponseDTO deleteUser(String id) {
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found."));
        user.setStatus(StatusEnum.INACTIVE);
        repository.save(user);
        return mapper.mapToResponseDTO(convertToDTO(user));
    }

    @Override
    public UserResponseDTO getInfor() {
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user = repository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found."));
        return mapper.mapToResponseDTO(convertToDTO(user));
    }

    @Override
    public UserResponseDTO update(UserRequestDTO dto, String id) {
        User existingUser = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found."));

        // Phương thức tiện ích để cập nhật nếu giá trị tồn tại và không rỗng
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

        // Các trường enum và ngày
        Optional.ofNullable(dto.getStatus()).ifPresent(existingUser::setStatus);
        Optional.ofNullable(dto.getRole()).ifPresent(existingUser::setRoleEnum);
        Optional.ofNullable(dto.getLevel()).ifPresent(existingUser::setLevelEnum);
        Optional.ofNullable(dto.getStartDate()).ifPresent(existingUser::setStartDate);
        Optional.ofNullable(dto.getEndDate()).ifPresent(existingUser::setEndDate);

        return mapper.mapToResponseDTO(convertToDTO(repository.save(existingUser)));
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
            throw new RuntimeException("Email không được để trống.");
        }
        Optional<User> user = repository.findByEmail(email);
        if (user.isEmpty()) {
            throw new RuntimeException("Email không tồn tại.");
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
        dto.setPassword(entity.getPassword());
        dto.setAvatar(entity.getAvatar());
        dto.setContentMotivation(entity.getContentMotivation());
        dto.setRole(entity.getRoleEnum());
        dto.setStatus(entity.getStatus());
        dto.setLevel(entity.getLevelEnum());
        dto.setStartDate();
        dto.setEndDate(entity.getEndDate());
        return dto;
    }
}
