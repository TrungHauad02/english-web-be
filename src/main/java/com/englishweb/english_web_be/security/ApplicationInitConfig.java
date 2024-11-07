package com.englishweb.english_web_be.security;

import com.englishweb.english_web_be.model.User;
import com.englishweb.english_web_be.modelenum.RoleEnum;
import com.englishweb.english_web_be.modelenum.StatusEnum;
import com.englishweb.english_web_be.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Slf4j
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if(userRepository.findByEmail("21110487@student.hcmute.edu.vn").isEmpty()){
                User user = new User(
                        "admin-id",                                        // Thay thế "admin-id" bằng một ID hợp lệ
                        "Hung",                                                // Tên người dùng
                        "21110487@student.hcmute.edu.vn",                      // Email
                        passwordEncoder.encode("123"),            // Mật khẩu đã mã hóa
                        null,                                                  // URL ảnh đại diện nếu có
                        null,                                                  // Nội dung động lực
                        StatusEnum.ACTIVE,                                     // Trạng thái người dùng (giả sử bạn có `StatusEnum.ACTIVE`)
                        RoleEnum.ADMIN,                                        // Vai trò người dùng (giả sử bạn có `RoleEnum.USER`)
                        null,                                                  // Cấp độ người dùng (giả sử bạn có `LevelEnum.BEGINNER`)
                        LocalDate.now(),                                       // Ngày bắt đầu
                        null                                                   // Ngày kết thúc (có thể là null nếu chưa xác định)
                );
                userRepository.save(user);                                     // Lưu người dùng vào cơ sở dữ liệu
                log.warn("Admin user has been created with default password: admin, please change it");
            }
            if(userRepository.findByEmail("21110487@student.hcmute.edu.vn").isEmpty()){
                User user = new User(
                        "admin-id",                                        // Thay thế "admin-id" bằng một ID hợp lệ
                        "Hung",                                                // Tên người dùng
                        "21110487@student.hcmute.edu.vn",                      // Email
                        passwordEncoder.encode("123"),            // Mật khẩu đã mã hóa
                        null,                                                  // URL ảnh đại diện nếu có
                        null,                                                  // Nội dung động lực
                        StatusEnum.ACTIVE,                                     // Trạng thái người dùng (giả sử bạn có `StatusEnum.ACTIVE`)
                        RoleEnum.ADMIN,                                        // Vai trò người dùng (giả sử bạn có `RoleEnum.USER`)
                        null,                                                  // Cấp độ người dùng (giả sử bạn có `LevelEnum.BEGINNER`)
                        LocalDate.now(),                                       // Ngày bắt đầu
                        null                                                   // Ngày kết thúc (có thể là null nếu chưa xác định)
                );
                userRepository.save(user);                                     // Lưu người dùng vào cơ sở dữ liệu
                log.warn("Admin user has been created with default password: admin, please change it");
            }
            if(userRepository.findByEmail("student").isEmpty()){
                User user = new User(
                        "user-id",                                        // Thay thế "admin-id" bằng một ID hợp lệ
                        "student",                                                // Tên người dùng
                        "student",                                              // Email
                        passwordEncoder.encode("123"),            // Mật khẩu đã mã hóa
                        null,                                                  // URL ảnh đại diện nếu có
                        null,                                                  // Nội dung động lực
                        StatusEnum.ACTIVE,                                     // Trạng thái người dùng (giả sử bạn có `StatusEnum.ACTIVE`)
                        RoleEnum.STUDENT,                                        // Vai trò người dùng (giả sử bạn có `RoleEnum.USER`)
                        null,                                                  // Cấp độ người dùng (giả sử bạn có `LevelEnum.BEGINNER`)
                        LocalDate.now(),                                       // Ngày bắt đầu
                        null                                                   // Ngày kết thúc (có thể là null nếu chưa xác định)
                );
                userRepository.save(user);                                     // Lưu người dùng vào cơ sở dữ liệu
                log.warn("student user has been created with default password: admin, please change it");
            }
            if(userRepository.findByEmail("teacher").isEmpty()){
                User user = new User(
                        "teacher-id",                                        // Thay thế "admin-id" bằng một ID hợp lệ
                        "teacher",                                                // Tên người dùng
                        "teacher",                                              // Email
                        passwordEncoder.encode("123"),            // Mật khẩu đã mã hóa
                        null,                                                  // URL ảnh đại diện nếu có
                        null,                                                  // Nội dung động lực
                        StatusEnum.ACTIVE,                                     // Trạng thái người dùng (giả sử bạn có `StatusEnum.ACTIVE`)
                        RoleEnum.TEACHER,                                        // Vai trò người dùng (giả sử bạn có `RoleEnum.USER`)
                        null,                                                  // Cấp độ người dùng (giả sử bạn có `LevelEnum.BEGINNER`)
                        LocalDate.now(),                                       // Ngày bắt đầu
                        null                                                   // Ngày kết thúc (có thể là null nếu chưa xác định)
                );
                userRepository.save(user);                                     // Lưu người dùng vào cơ sở dữ liệu
                log.warn("teacher user has been created with default password: admin, please change it");
            }
        };
    }
}
