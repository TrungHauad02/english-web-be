package com.englishweb.english_web_be.service;

import com.englishweb.english_web_be.dto.VerificationDTO;
import com.englishweb.english_web_be.modelenum.RoleEnum;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.englishweb.english_web_be.dto.UserDTO;
import com.englishweb.english_web_be.model.User;
import com.englishweb.english_web_be.repository.UserRepository;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class UserService extends BaseService<User, UserDTO, UserRepository> {

    PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private EmailService emailService;
    private final Map<String, String> otpStore = new HashMap<>();

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, UserRepository userRepository, EmailService emailService) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public UserDTO update(UserDTO dto, String id) {
        User existingUser = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found."));

        if (!passwordEncoder.matches(dto.getPassword(), existingUser.getPassword())) {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        } else {
            dto.setPassword(existingUser.getPassword());
        }

        return super.update(dto, id);
    }

    @Override
    public UserDTO create(UserDTO dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists. Please use another email.");
        }

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        dto.setStatus(StatusEnum.ACTIVE);
        dto.setRole(RoleEnum.STUDENT);
        return super.create(dto);
    }

    public UserDTO createTeacher(UserDTO dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists. Please use another email.");
        }

        // Tạo mật khẩu ngẫu nhiên
        String rawPassword = generateRandomPassword(8); // Độ dài mật khẩu là 8 ký tự
        dto.setPassword(passwordEncoder.encode(rawPassword));

        dto.setStatus(StatusEnum.ACTIVE);
        dto.setRole(RoleEnum.TEACHER);

        // Gửi mật khẩu qua email
        emailService.sendPasswordByEmail(dto.getEmail(), rawPassword);

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

    public UserDTO getInfor(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByEmail(name).orElseThrow(() -> new RuntimeException("User not found."));
        return convertToDTO(user);
    }

    public void sendOtpToEmail(UserDTO dto) {
        // Kiểm tra xem email có tồn tại trong database không
        if (repository.findByEmail(dto.getEmail()).isEmpty()) {
            throw new RuntimeException("Email not exist");
        }

        // Tạo mã OTP ngẫu nhiên và lưu vào otpStore
        String otp = generateOtp();
        otpStore.put(dto.getEmail(), otp);

        // Gửi OTP qua email
        emailService.sendOtpByEmail(dto.getEmail(), otp); // Gửi OTP qua email
    }

    public boolean verifyOtp(VerificationDTO dto) {
        String storedOtp = otpStore.get(dto.getEmail());

        if (storedOtp == null) {
            throw new RuntimeException("OTP không tồn tại hoặc đã hết hạn.");
        }

        if (storedOtp.equals(dto.getOtp())) {
            otpStore.remove(dto.getEmail());
            return true;
        } else {
            return false;
        }
    }

    // Bước 3: Đặt lại mật khẩu
    public void resetPassword(VerificationDTO dto, String newPassword) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new RuntimeException("Email không tồn tại."));
        user.setPassword(passwordEncoder.encode(newPassword)); // Mã hóa mật khẩu mới
        userRepository.save(user);
    }

    // Hàm tạo mã OTP ngẫu nhiên
    private String generateOtp() {
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000); // Tạo OTP 6 chữ số
        return String.valueOf(otp);
    }

    private String generateRandomPassword(int length) {
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialChars = "!@#$%^&*()-_=+<>?";
        String allChars = upperCase + lowerCase + digits + specialChars;

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Đảm bảo mật khẩu có ít nhất một chữ hoa, một chữ thường, một chữ số và một ký tự đặc biệt
        password.append(upperCase.charAt(random.nextInt(upperCase.length())));
        password.append(lowerCase.charAt(random.nextInt(lowerCase.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(specialChars.charAt(random.nextInt(specialChars.length())));

        // Thêm các ký tự ngẫu nhiên cho đến khi đủ độ dài yêu cầu
        for (int i = 4; i < length; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        return password.toString();
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
